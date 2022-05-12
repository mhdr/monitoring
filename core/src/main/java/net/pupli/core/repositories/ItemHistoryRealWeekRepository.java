package net.pupli.core.repositories;

import net.pupli.core.models.ItemHistoryRealWeek;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemHistoryRealWeekRepository extends MongoRepository<ItemHistoryRealWeek, String> {
    List<ItemHistoryRealWeek> findAllByTimeBefore(DateTime time);
}
