package net.pupli.web_server.repositories;

import net.pupli.web_server.models.ItemHistoryReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemHistoryRealRepository extends MongoRepository<ItemHistoryReal, String> {
}
