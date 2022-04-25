package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.repositories.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyContext.mongoTemplate = applicationContext.getBean(MongoTemplate.class);
        MyContext.amqpTemplate = (AmqpTemplate) applicationContext.getBean("rabbitTemplate");
        MyContext.redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        MyContext.monitoringItemRepository = applicationContext.getBean(MonitoringItemRepository.class);
        MyContext.myCache = applicationContext.getBean(MyCache.class);
        MyContext.rawRealDataRepository = applicationContext.getBean(RawRealDataRepository.class);
        MyContext.rawBooleanDataRepository = applicationContext.getBean(RawBooleanDataRepository.class);
        MyContext.interfaceCredentialRepository = applicationContext.getBean(InterfaceCredentialRepository.class);
        MyContext.finalRealDataRepository = applicationContext.getBean(FinalRealDataRepository.class);
        MyContext.finalBooleanDataRepository = applicationContext.getBean(FinalBooleanDataRepository.class);
        MyContext.prevRealDataRepository = applicationContext.getBean(PrevRealDataRepository.class);
        MyContext.prevRealDataSavedRepository = applicationContext.getBean(PrevRealDataSavedRepository.class);
        MyContext.realItemHistoryRepository = applicationContext.getBean(RealItemHistoryRepository.class);
        MyContext.realItemHistoryWeekRepository = applicationContext.getBean(RealItemHistoryWeekRepository.class);
    }
}
