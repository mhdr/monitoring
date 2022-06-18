package net.pupli.web_server.repositories;

import net.pupli.web_server.models.ItemHistoryBooleanWeek;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemHistoryBooleanWeekRepository extends MongoRepository<ItemHistoryBooleanWeek, String> {
    List<ItemHistoryBooleanWeek> findAllByTimeBefore(DateTime time);
}
