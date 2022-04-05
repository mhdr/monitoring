package net.pupli.core.services;

import net.pupli.core.libs.DB;
import net.pupli.core.repositories.MonitoringItemRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DB.mongoTemplate=applicationContext.getBean(MongoTemplate.class);
        DB.monitoringItemRepository = applicationContext.getBean(MonitoringItemRepository.class);
    }
}
