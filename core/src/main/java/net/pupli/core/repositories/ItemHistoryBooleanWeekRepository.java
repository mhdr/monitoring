package net.pupli.core.repositories;

import net.pupli.core.models.ItemHistoryBooleanWeek;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemHistoryBooleanWeekRepository extends MongoRepository<ItemHistoryBooleanWeek, String> {
}
