package net.pupli.core.libs;

import net.pupli.core.repositories.MonitoringItemRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DB {
    public static MongoTemplate mongoTemplate = null;
    public static MonitoringItemRepository monitoringItemRepository = null;
}
