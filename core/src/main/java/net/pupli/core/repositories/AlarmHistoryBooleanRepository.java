package net.pupli.core.repositories;

import net.pupli.core.models.AlarmHistoryBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmHistoryBooleanRepository extends MongoRepository<AlarmHistoryBoolean, String> {
}
