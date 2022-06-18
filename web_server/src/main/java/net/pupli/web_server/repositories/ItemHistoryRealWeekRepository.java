package net.pupli.web_server.repositories;

import net.pupli.web_server.models.ItemHistoryRealWeek;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemHistoryRealWeekRepository extends MongoRepository<ItemHistoryRealWeek, String> {
    List<ItemHistoryRealWeek> findAllByTimeBefore(DateTime time);
}
