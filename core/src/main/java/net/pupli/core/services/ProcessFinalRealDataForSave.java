package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.RealItemHistory;
import net.pupli.core.models.RealItemHistoryWeek;
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
public class ProcessFinalRealDataForSave implements CommandLineRunner {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessFinalRealDataForSave.class);

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(() -> {
            try {
                while (true) {
                    try {
                        var finalRealDataList = MyContext.finalRealDataRepository.findAll();
                        var prevRealDataList = MyContext.prevRealDataRepository.findAll();

                        var newItemsHistory = new ArrayList<RealItemHistory>();
                        var newItemsHistoryWeek = new ArrayList<RealItemHistoryWeek>();

                        for (var data : finalRealDataList) {
                            try {

                                if (data.getValue() == null || data.getTime() == null) {
                                    continue;
                                }

                                var prevData = prevRealDataList
                                        .stream()
                                        .filter(x -> Objects.equals(x.getItemId(), data.getItemId()))
                                        .findFirst();

                                if (prevData.isPresent()) {
                                    // if prev data is available we should check the last time we saved data
                                    var prevDataValue = prevData.get();

                                    if (prevDataValue.getValue() == null || prevDataValue.getTime() == null) {
                                        // prev data is null so we should just save current data

                                        // add new item history
                                        RealItemHistory realItemHistory = new RealItemHistory(data.getItemId(), data.getValue(), data.getTime());
                                        newItemsHistory.add(realItemHistory);

                                        // add new item history for week
                                        RealItemHistoryWeek realItemHistoryWeek = new RealItemHistoryWeek(data.getItemId(), data.getValue(), data.getTime());
                                        newItemsHistoryWeek.add(realItemHistoryWeek);

                                        // update prev data
                                        prevDataValue.setValue(data.getValue());
                                        prevDataValue.setTime(data.getTime());
                                    } else {

                                        // we should check whether we should save data or not
                                        // saving data is based on interval

                                        var item = MyContext.myCache.getItems().get(data.getItemId());

                                        var diff = Seconds.secondsBetween(prevDataValue.getTime(), data.getTime());
                                        if (diff.getSeconds() > item.getInterval()) {
                                            // add new item history
                                            RealItemHistory realItemHistory = new RealItemHistory(data.getItemId(), data.getValue(), data.getTime());
                                            newItemsHistory.add(realItemHistory);

                                            // add new item history for week
                                            RealItemHistoryWeek realItemHistoryWeek = new RealItemHistoryWeek(data.getItemId(), data.getValue(), data.getTime());
                                            newItemsHistoryWeek.add(realItemHistoryWeek);

                                            // update prev data
                                            prevDataValue.setValue(data.getValue());
                                            prevDataValue.setTime(data.getTime());
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                logger.error(ex.getMessage(), ex);
                            }
                        }


                        MyContext.realItemHistoryRepository.saveAll(newItemsHistory);
                        MyContext.realItemHistoryWeekRepository.saveAll(newItemsHistoryWeek);
                        MyContext.prevRealDataRepository.saveAll(prevRealDataList);

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
