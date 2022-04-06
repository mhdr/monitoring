package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.repositories.mongodb.MonitoringItemRepository;
import net.pupli.core.repositories.redis.RawRealDataRepository;
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
        MyContext.monitoringItemRepository = applicationContext.getBean(MonitoringItemRepository.class);
        MyContext.amqpTemplate = (AmqpTemplate) applicationContext.getBean("rabbitTemplate");
        MyContext.redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        MyContext.staticCache = applicationContext.getBean(StaticCache.class);
        MyContext.rawRealDataRepository = applicationContext.getBean(RawRealDataRepository.class);
    }
}
