package net.pupli.core.repositories;

import net.pupli.core.models.MonitoringItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringItemRepository extends MongoRepository<MonitoringItem, String> {

}
