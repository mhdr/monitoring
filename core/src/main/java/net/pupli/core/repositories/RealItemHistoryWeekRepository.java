package net.pupli.core.repositories;

import net.pupli.core.models.RealItemHistory;
import net.pupli.core.models.RealItemHistoryWeek;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RealItemHistoryWeekRepository extends MongoRepository<RealItemHistoryWeek, String> {
}
