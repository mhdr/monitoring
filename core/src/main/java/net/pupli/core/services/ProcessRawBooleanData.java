package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProcessRawBooleanData implements CommandLineRunner {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessRawBooleanData.class);

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
                            var rawDataList = MyContext.rawBooleanDataRepository.findAll();
                            var dataList = MyContext.finalBooleanDataRepository.findAll();

                            for (var rawData : rawDataList) {
                                var optionalData = dataList.stream()
                                        .filter(x -> Objects.equals(x.getItemId(), rawData.getItemId())).findFirst();

                                // check if data is available on final data collection then update it
                                if (optionalData.isPresent()) {
                                    var data = optionalData.get();
                                    data.setValue(rawData.getValue());
                                    data.setTime(rawData.getTime());
                                }
                            }

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
