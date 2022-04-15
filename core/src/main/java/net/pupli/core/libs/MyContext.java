package net.pupli.core.libs;

import net.pupli.core.models.RealData;
import net.pupli.core.repositories.*;
import net.pupli.core.services.MyCache;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MyContext {
    public static MongoTemplate mongoTemplate = null;
    public static AmqpTemplate amqpTemplate = null;
    public static MyCache myCache = null;
    public static MonitoringItemRepository monitoringItemRepository = null;
    public static RawRealDataRepository rawRealDataRepository = null;
    public static RawBooleanDataRepository rawBooleanDataRepository = null;
    public static InterfaceCredentialRepository interfaceCredentialRepository = null;
    public static RealDataRepository realDataRepository = null;
    public static BooleanDataRepository booleanDataRepository = null;
}
