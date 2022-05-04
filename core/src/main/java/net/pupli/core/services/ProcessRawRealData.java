package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
// process raw data and put them on final data collection
public class ProcessRawRealData implements CommandLineRunner {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessRawRealData.class);

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
                            var rawDataList = MyContext.rawRealDataRepository.findAll();
                            var dataList = MyContext.finalRealDataRepository.findAll();

                            for (var rawData : rawDataList) {
                                var optionalData = dataList.stream()
                                        .filter(x -> Objects.equals(x.getItemId(), rawData.getItemId())).findFirst();

                                // check if data is available on final data collection
                                if (optionalData.isPresent()) {
                                    var data = optionalData.get();

                                    if (rawData.getTime() == null || rawData.getTime().isEmpty()) {
                                        continue;
                                    }

                                    if (rawData.getValue() == null || rawData.getValue().isEmpty()) {
                                        continue;
                                    }

                                    DateTime t = DateTime.parse(rawData.getTime());
                                    Double v = Double.parseDouble(rawData.getValue());

                                    data.setValue(v);
                                    data.setTime(t);
                                }
                            }

                            MyContext.finalRealDataRepository.saveAll(dataList);
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
