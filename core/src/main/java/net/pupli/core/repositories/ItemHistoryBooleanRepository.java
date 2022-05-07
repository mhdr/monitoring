package net.pupli.core.repositories;

import net.pupli.core.models.ItemHistoryBoolean;
import net.pupli.core.models.ItemHistoryReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemHistoryBooleanRepository extends MongoRepository<ItemHistoryBoolean, String> {
}
