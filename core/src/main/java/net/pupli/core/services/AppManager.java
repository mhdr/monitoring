package net.pupli.core.services;

import net.pupli.core.libs.InitDb;
import net.pupli.core.libs.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class AppManager {

    Logger logger = LoggerFactory.getLogger(AppManager.class);

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        InitDb initDb = new InitDb();

        initDb.initRawRealData();
        initDb.initRawBooleanData();

        MyContext.myCache.loadItems();
        MyContext.myCache.loadRawItems();

        initDb.initFinalRealData();
        initDb.initFinalBooleanData();

        initDb.initPrevRealData();

        // should start processing data after starting app
        MyContext.myCache.setShouldProcessData(true);
        logger.info("Started data processing...");
    }

    @PreDestroy
    public void destroy() {
        MyContext.myCache.setShouldProcessData(false);
        logger.info("Stopping data processing...");
        MyContext.rawRealDataRepository.deleteAll();
        MyContext.rawBooleanDataRepository.deleteAll();
    }
}
