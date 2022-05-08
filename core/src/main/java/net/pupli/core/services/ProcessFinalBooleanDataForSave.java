package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.ItemHistoryBoolean;
import net.pupli.core.models.ItemHistoryBooleanWeek;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
// process final data and put them on items history
// when we should save data on items history collection?
// 1 - after interval elapsed => we will detect it here
// 2 - if current data changes alarm flag => we can not detect it here on this class
// 3 - onChange data for boolean data => we will detect it here
// so we detect if data should be saved on items history ( 1 or 3 ) then send it to appropriate queue
public class ProcessFinalBooleanDataForSave implements CommandLineRunner {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    Logger logger = LoggerFactory.getLogger(ProcessFinalBooleanDataForSave.class);

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(() -> {
            try {
                while (true) {
                    try {
                        var finalDataList = MyContext.finalBooleanDataRepository.findAll();
                        var prevDataList = MyContext.prevBooleanDataRepository.findAll();

                        var newItemsHistory = new ArrayList<ItemHistoryBoolean>();
                        var newItemsHistoryWeek = new ArrayList<ItemHistoryBooleanWeek>();

                        for (var data : finalDataList) {
                            try {
                                if (data.getValue() == null || data.getTime() == null) {
                                    continue;
                                }

                                var prevData = prevDataList
                                        .stream()
                                        .filter(x -> Objects.equals(x.getItemId(), data.getItemId()))
                                        .findFirst();

                                var shouldSave = false;

                                if (prevData.isPresent()) {
                                    // if prev data is available we should check the last time we saved data
                                    var prevDataValue = prevData.get();

                                    if (prevDataValue.getValue() == null || prevDataValue.getTime() == null) {
                                        // prev data is null so we should just save current data
                                        shouldSave = true;
                                    } else {

                                        // we should check whether we should save data or not
                                        // saving data is based on interval or onChange

                                        var item = MyContext.myCache.getItems().get(data.getItemId());

                                        // for boolean data we should first we should check onChange
                                        if (data.getValue() != prevDataValue.getValue()) {
                                            shouldSave = true;
                                        } else {
                                            // then we should check interval
                                            var diff = Seconds.secondsBetween(prevDataValue.getTime(), data.getTime());
                                            if (diff.getSeconds() > item.getInterval()) {
                                                shouldSave = true;
                                            }
                                        }
                                    }

                                    if (shouldSave) {
                                        // add new item history
                                        var itemHistory = new ItemHistoryBoolean(data.getItemId(), data.getValue(), data.getTime());
                                        newItemsHistory.add(itemHistory);

                                        // add new item history for week
                                        var itemHistoryWeek = new ItemHistoryBooleanWeek(data.getItemId(), data.getValue(), data.getTime());
                                        newItemsHistoryWeek.add(itemHistoryWeek);

                                        // update prev data
                                        prevDataValue.setValue(data.getValue());
                                        prevDataValue.setTime(data.getTime());
                                    }

                                }
                            } catch (Exception ex) {
                                logger.error(ex.getMessage(), ex);
                            }
                        }

                        MyContext.itemHistoryBooleanRepository.saveAll(newItemsHistory);
                        MyContext.itemHistoryBooleanWeekRepository.saveAll(newItemsHistoryWeek);
                        MyContext.prevBooleanDataRepository.saveAll(prevDataList);

                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    } finally {
                        // check data every 1 second
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }
}
