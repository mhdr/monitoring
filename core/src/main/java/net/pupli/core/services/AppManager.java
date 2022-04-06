package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.RawBooleanData;
import net.pupli.core.models.RawRealData;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class AppManager {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MyContext.mongoTemplate.dropCollection(RawRealData.class);
        MyContext.mongoTemplate.dropCollection(RawBooleanData.class);
        MyContext.myCache.loadItems();
        MyContext.myCache.loadRawItems();
        // should start processing data after starting app
        MyContext.myCache.setShouldProcessData(true);
    }

    @PreDestroy
    public void destroy() {
        MyContext.myCache.setShouldProcessData(false);
        MyContext.mongoTemplate.dropCollection(RawRealData.class);
        MyContext.mongoTemplate.dropCollection(RawBooleanData.class);
    }
}
