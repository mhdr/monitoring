package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProcessFinalBooleanDataForAlarm implements CommandLineRunner {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessFinalBooleanDataForAlarm.class);
    private volatile List<FinalBooleanData> finalBooleanDataList;
    private volatile List<ItemHistoryBoolean> newItemsHistory;
    private volatile List<ItemHistoryBooleanWeek> newItemsHistoryWeek;
    private volatile List<AlarmHistoryBoolean> alarmsHistory;
    private volatile List<AlarmStatusBoolean> alarmsStatus;

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(() -> {
            try {
                while (true) {
                    try {
                        var alarms = MyContext.alarmBooleanRepository.findAll();
                        alarmsStatus = MyContext.alarmStatusBooleanRepository.findAll();
                        finalBooleanDataList = MyContext.finalBooleanDataRepository.findAll();
                        newItemsHistory = new ArrayList<>();
                        newItemsHistoryWeek = new ArrayList<>();
                        alarmsHistory = new ArrayList<>();

                        finalBooleanDataList.parallelStream().forEach(data -> {
                            try {
                                if (data.getValue() != null) {
                                    var alarmsForCurrent = alarms
                                            .stream()
                                            .filter(x -> Objects.equals(x.getItemId(), data.getItemId()))
                                            .toList();

                                    alarmsForCurrent.forEach(alarm -> {

                                        var hasAlarm = false;
                                        var shouldSave = false;
                                        var alarmStatus = alarmsStatus
                                                .stream()
                                                .filter(x -> Objects.equals(x.getAlarmId(), alarm.getId()))
                                                .findFirst();

                                        if (alarmStatus.isPresent()) {
                                            var alarmStatusValue = alarmStatus.get();

                                            // 1 => compare values
                                            if (alarm.getAlarmType() == 1) {
                                                // 1 => equal
                                                if (alarm.getCompareProps().getCompareType() == 1) {
                                                    if (Objects.equals(data.getValue(), alarm.getCompareProps().getValue())) {
                                                        hasAlarm = true;
                                                    }
                                                }
                                                // 2 => not equal
                                                else if (alarm.getCompareProps().getCompareType() == 2) {
                                                    if (!Objects.equals(data.getValue(), alarm.getCompareProps().getValue())) {
                                                        hasAlarm = true;
                                                    }
                                                }

                                                if (hasAlarm) {
                                                    if (!alarmStatusValue.isSuspicious()) {
                                                        alarmStatusValue.setSuspicious(true);
                                                        alarmStatusValue.setSuspiciousTime(DateTime.now());
                                                    } else {
                                                        if (alarmStatusValue.getSuspiciousTime()
                                                                .plusSeconds(alarm.getCompareProps().getDelay())
                                                                .isBeforeNow()) {
                                                            if (!alarmStatusValue.isHasAlarm()) {
                                                                alarmStatusValue.setHasAlarm(true);
                                                                alarmStatusValue.setAlarmTime(DateTime.now());
                                                                shouldSave = true;
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (alarmStatusValue.isHasAlarm()) {
                                                        alarmStatusValue.setSuspicious(false);
                                                        alarmStatusValue.setSuspiciousTime(null);
                                                        alarmStatusValue.setHasAlarm(false);
                                                        alarmStatusValue.setAlarmTime(null);
                                                        shouldSave = true;
                                                    }
                                                }
                                            }
                                            // 2 => timeout values
                                            else if (alarm.getAlarmType() == 2) {
                                                if (data.getTime().plusSeconds(alarm.getTimeoutProps().getValue()).isBeforeNow()) {
                                                    hasAlarm = true;
                                                }

                                                if (hasAlarm) {
                                                    if (!alarmStatusValue.isHasAlarm()) {
                                                        alarmStatusValue.setSuspicious(true);
                                                        alarmStatusValue.setSuspiciousTime(DateTime.now());
                                                        alarmStatusValue.setHasAlarm(true);
                                                        alarmStatusValue.setAlarmTime(DateTime.now());
                                                        shouldSave = true;
                                                    }
                                                } else {
                                                    if (alarmStatusValue.isHasAlarm()) {
                                                        alarmStatusValue.setSuspicious(false);
                                                        alarmStatusValue.setSuspiciousTime(null);
                                                        alarmStatusValue.setHasAlarm(false);
                                                        alarmStatusValue.setAlarmTime(null);
                                                        shouldSave = true;
                                                    }
                                                }
                                            }
                                        }

                                        if (shouldSave) {
                                            // add new item history
                                            var itemHistoryBoolean = new ItemHistoryBoolean(data.getItemId(), data.getValue(), data.getTime());
                                            newItemsHistory.add(itemHistoryBoolean);

                                            // add new item history for week
                                            var itemHistoryBooleanWeek = new ItemHistoryBooleanWeek(data.getItemId(), data.getValue(), data.getTime());
                                            newItemsHistoryWeek.add(itemHistoryBooleanWeek);

                                            // add new alarm history
                                            var alarmHistoryBoolean = new AlarmHistoryBoolean(alarm.getId(), hasAlarm, DateTime.now());
                                            alarmsHistory.add(alarmHistoryBoolean);
                                        }

                                    });
                                }
                            } catch (Exception ex) {
                                logger.error(ex.getMessage(), ex);
                            }
                        });

                        MyContext.alarmStatusBooleanRepository.saveAll(alarmsStatus);
                        MyContext.itemHistoryBooleanRepository.saveAll(newItemsHistory);
                        MyContext.itemHistoryBooleanWeekRepository.saveAll(newItemsHistoryWeek);
                        MyContext.alarmHistoryBooleanRepository.saveAll(alarmsHistory);

                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    } finally {
                        Thread.sleep(1000);
                    }
                }

            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }
}
