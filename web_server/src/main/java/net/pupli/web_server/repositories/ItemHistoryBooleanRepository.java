package net.pupli.web_server.repositories;

import net.pupli.web_server.models.ItemHistoryBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemHistoryBooleanRepository extends MongoRepository<ItemHistoryBoolean, String> {
}
