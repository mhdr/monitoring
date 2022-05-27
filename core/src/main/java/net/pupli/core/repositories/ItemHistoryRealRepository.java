package net.pupli.core.repositories;

import net.pupli.core.models.ItemHistoryReal;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface ItemHistoryRealRepository extends MongoRepository<ItemHistoryReal, String> {
}
