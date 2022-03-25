package net.pupli.core.repositories;

import net.pupli.core.models.MonitoringItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonitoringItemRepository extends MongoRepository<MonitoringItem, String> {

}
