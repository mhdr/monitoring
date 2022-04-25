package net.pupli.core.repositories;

import net.pupli.core.models.RealItemHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RealItemHistoryRepository extends MongoRepository<RealItemHistory, String> {
}
