package net.pupli.core.services;

import com.google.gson.*;
import net.pupli.core.libs.GsonBuilders;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.FinalRealData;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
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
                        for (var data : finalRealDataList) {
                            try {

                                if (data.getValue() == null || data.getTime() == null) {
                                    continue;
                                }

                                var prevData = MyContext.prevRealDataSavedRepository.findById(data.getItemId());

                                if (prevData.isPresent()) {
                                    // if prev data is available we should check the last time we saved data
                                    var prevDataValue = prevData.get();
                                    var item = MyContext.myCache.getItems().get(data.getItemId());

                                    var diff = Seconds.secondsBetween(prevDataValue.getTime(), data.getTime());
                                    if (diff.getSeconds() > item.getInterval()) {
                                        // save data

                                        Gson gson = GsonBuilders.gsonDateTime();

                                        String str = gson.toJson(data, FinalRealData.class);
                                        MyContext.amqpTemplate.convertAndSend("MonitoringV5_Queue7", str);
                                    }
                                } else {
                                    // it's the first time we are going to save data in prev collection, so we should save data

                                    Gson gson = GsonBuilders.gsonDateTime();

                                    String str = gson.toJson(data, FinalRealData.class);
                                    MyContext.amqpTemplate.convertAndSend("MonitoringV5_Queue7", str);
                                }
                            } catch (Exception ex) {
                                logger.error(ex.getMessage(), ex);
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    } finally {
                        Thread.sleep(100);
                    }
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }
}
