package net.pupli.core.repositories;

import net.pupli.core.models.ItemHistoryBooleanWeek;
import net.pupli.core.models.ItemHistoryRealWeek;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @since 2022-05-27
 */
public interface ItemHistoryBooleanWeekRepository extends MongoRepository<ItemHistoryBooleanWeek, String> {
    List<ItemHistoryBooleanWeek> findAllByTimeBefore(DateTime time);
}
