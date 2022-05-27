package net.pupli.core.repositories;

import net.pupli.core.models.AlarmHistoryBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface AlarmHistoryBooleanRepository extends MongoRepository<AlarmHistoryBoolean, String> {
}
