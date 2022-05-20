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
public class ProcessFinalRealDataForAlarm implements CommandLineRunner {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessFinalRealDataForAlarm.class);

    private volatile List<FinalRealData> finalRealDataList;
    private volatile List<ItemHistoryReal> newItemsHistory;
    private volatile List<ItemHistoryRealWeek> newItemsHistoryWeek;
    private volatile List<AlarmHistoryReal> alarmsHistory;
    private volatile List<AlarmStatusReal> alarmsStatus;

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(() -> {
            try {
                while (true) {
                    try {
                        var alarms = MyContext.alarmRealRepository.findAll();
                        alarmsStatus = MyContext.alarmStatusRealRepository.findAll();
                        finalRealDataList = MyContext.finalRealDataRepository.findAll();
                        newItemsHistory = new ArrayList<>();
                        newItemsHistoryWeek = new ArrayList<>();
                        alarmsHistory = new ArrayList<>();

                        finalRealDataList.parallelStream().forEach(data -> {

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
                                            // 1 => lower
                                            if (alarm.getCompareProps().getCompareType() == 1) {
                                                if (data.getValue() < alarm.getCompareProps().getValue()) {
                                                    hasAlarm = true;
                                                }
                                            }
                                            // 2 => higher
                                            else if (alarm.getCompareProps().getCompareType() == 2) {
                                                if (data.getValue() > alarm.getCompareProps().getValue()) {
                                                    hasAlarm = true;
                                                }
                                            }
                                            // 3 => between
                                            else if (alarm.getCompareProps().getCompareType() == 3) {
                                                if (data.getValue() > alarm.getCompareProps().getValue() &&
                                                        data.getValue() < alarm.getCompareProps().getValue2()) {
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
                                        ItemHistoryReal itemHistoryReal = new ItemHistoryReal(data.getItemId(), data.getValue(), data.getTime());
                                        newItemsHistory.add(itemHistoryReal);

                                        // add new item history for week
                                        ItemHistoryRealWeek itemHistoryRealWeek = new ItemHistoryRealWeek(data.getItemId(), data.getValue(), data.getTime());
                                        newItemsHistoryWeek.add(itemHistoryRealWeek);

                                        // add new alarm history
                                        AlarmHistoryReal alarmHistoryReal = new AlarmHistoryReal(alarm.getId(), hasAlarm, DateTime.now());
                                        alarmsHistory.add(alarmHistoryReal);
                                    }

                                });
                            }
                        });

                        MyContext.alarmStatusRealRepository.saveAll(alarmsStatus);
                        MyContext.itemHistoryRealRepository.saveAll(newItemsHistory);
                        MyContext.itemHistoryRealWeekRepository.saveAll(newItemsHistoryWeek);
                        MyContext.alarmHistoryRealRepository.saveAll(alarmsHistory);

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
