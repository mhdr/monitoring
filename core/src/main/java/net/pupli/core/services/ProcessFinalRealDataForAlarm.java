package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProcessFinalRealDataForAlarm implements CommandLineRunner {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Logger logger = LoggerFactory.getLogger(ProcessFinalRealDataForAlarm.class);

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(() -> {
            try {

            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }
}
