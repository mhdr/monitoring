package net.pupli.web_server.repositories;

import net.pupli.web_server.models.MonitoringItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringItemRepository extends MongoRepository<MonitoringItem, String> {

}