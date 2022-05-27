package net.pupli.core.repositories;

import net.pupli.core.models.AlarmBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface AlarmBooleanRepository extends MongoRepository<AlarmBoolean, String> {
}
