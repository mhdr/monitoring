package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.FinalBooleanData;
import net.pupli.core.models.RawBooleanData;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProcessRawBooleanData implements CommandLineRunner {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessRawBooleanData.class);

    private volatile List<RawBooleanData> rawDataList;
    private volatile List<FinalBooleanData> dataList;

    @Override
    public void run(String... args) {

        executorService.execute(() -> {
            try {
                while (true) {
                    try {
                        // check if we are allowed to do processing data
                        if (MyContext.myCache.getShouldProcessData()) {
                            // iterate through all raw data which are available in raw collection in mongodb
                            // and check if that related data is available on final data collection
                            // id data is present update it else create a new record
                            rawDataList = MyContext.rawBooleanDataRepository.findAll();
                            dataList = MyContext.finalBooleanDataRepository.findAll();

                            rawDataList.parallelStream().forEach(rawData -> {
                                try {
                                    var optionalData = dataList.stream()
                                            .filter(x -> Objects.equals(x.getItemId(), rawData.getItemId())).findFirst();

                                    // check if data is available on final data collection then update it
                                    if (optionalData.isPresent()) {
                                        var data = optionalData.get();

                                        if (rawData.getTime() != null && !rawData.getTime().isEmpty() &&
                                                rawData.getValue() != null && !rawData.getValue().isEmpty()) {
                                            var t = DateTime.parse(rawData.getTime());
                                            var v = Objects.equals(rawData.getValue(), "1");

                                            data.setValue(v);
                                            data.setTime(t);
                                        }
                                    }
                                } catch (Exception ex) {
                                    logger.error(ex.getMessage(), ex);
                                }
                            });

                            MyContext.finalBooleanDataRepository.saveAll(dataList);
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
