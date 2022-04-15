package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.RawBooleanData;
import net.pupli.core.models.RawRealData;
import net.pupli.core.models.RealData;
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

                        if (MyContext.myCache.getShouldProcessData()) {
                            var rawDataList = MyContext.rawBooleanDataRepository.findAll();
                            var dataList = MyContext.booleanDataRepository.findAll();

                            for (RawBooleanData rawData : rawDataList) {
                                var optionalData = dataList.stream()
                                        .filter(x -> Objects.equals(x.getItemId(), rawData.getItemId())).findFirst();
                                if (optionalData.isPresent()) {
                                    var data = optionalData.get();

                                    if (data.getTime() == null) {
                                        data.setPrevValue(rawData.getValue());
                                        data.setPrevTime(rawData.getTime());
                                    } else if (rawData.getTime().isAfter(data.getTime())) {
                                        data.setPrevValue(data.getValue());
                                        data.setPrevTime(data.getTime());
                                    }

                                    data.setValue(rawData.getValue());
                                    data.setTime(rawData.getTime());
                                }
                            }

                            MyContext.booleanDataRepository.saveAll(dataList);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
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
