package net.pupli.core.libs;

import net.pupli.core.repositories.*;
import net.pupli.core.services.MyCache;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class MyContext {
    public static MongoTemplate mongoTemplate = null;
    public static AmqpTemplate amqpTemplate = null;
    public static StringRedisTemplate redisTemplate = null;
    public static MyCache myCache = null;
    public static MonitoringItemRepository monitoringItemRepository = null;
    public static RawRealDataRepository rawRealDataRepository = null;
    public static RawBooleanDataRepository rawBooleanDataRepository = null;
    public static InterfaceCredentialRepository interfaceCredentialRepository = null;
    public static FinalRealDataRepository finalRealDataRepository = null;
    public static FinalBooleanDataRepository finalBooleanDataRepository = null;
    public static PrevRealDataRepository prevRealDataRepository = null;
    public static PrevBooleanDataRepository prevBooleanDataRepository = null;
    public static ItemHistoryRealRepository itemHistoryRealRepository = null;
    public static ItemHistoryRealWeekRepository itemHistoryRealWeekRepository = null;
    public static ItemHistoryBooleanRepository itemHistoryBooleanRepository = null;
    public static ItemHistoryBooleanWeekRepository itemHistoryBooleanWeekRepository = null;
    public static AlarmRealRepository alarmRealRepository = null;
    public static AlarmHistoryRealRepository alarmHistoryRealRepository = null;

}
