package net.pupli.core.repositories;

import net.pupli.core.models.ItemHistoryReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemHistoryRealRepository extends MongoRepository<ItemHistoryReal, String> {
}
