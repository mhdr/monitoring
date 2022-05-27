package net.pupli.core.repositories;

import net.pupli.core.models.AlarmStatusBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface AlarmStatusBooleanRepository extends MongoRepository<AlarmStatusBoolean,String> {
}
