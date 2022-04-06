package net.pupli.core.libs;

import net.pupli.core.repositories.mongodb.MonitoringItemRepository;
import net.pupli.core.repositories.redis.RawRealDataRepository;
import net.pupli.core.services.StaticCache;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class MyContext {
    public static MongoTemplate mongoTemplate = null;
    public static MonitoringItemRepository monitoringItemRepository = null;
    public static AmqpTemplate amqpTemplate = null;
    public static StringRedisTemplate redisTemplate = null;
    public static StaticCache staticCache = null;
    public static RawRealDataRepository rawRealDataRepository = null;
}
