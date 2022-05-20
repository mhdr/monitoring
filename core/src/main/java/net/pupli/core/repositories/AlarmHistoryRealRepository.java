package net.pupli.core.repositories;

import net.pupli.core.models.AlarmHistoryReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmHistoryRealRepository extends MongoRepository<AlarmHistoryReal, String> {
}