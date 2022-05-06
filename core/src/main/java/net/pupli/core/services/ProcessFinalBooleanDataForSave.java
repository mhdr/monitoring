package net.pupli.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        try {
            while (true)
            {
                try {

                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                }
                finally {
                    // check data every 1 second
                    Thread.sleep(1000);
                }
            }
        }catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }
}
