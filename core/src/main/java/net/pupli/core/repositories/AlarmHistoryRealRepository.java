package net.pupli.core.repositories;

import net.pupli.core.models.AlarmHistoryReal;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface AlarmHistoryRealRepository extends MongoRepository<AlarmHistoryReal, String> {
}