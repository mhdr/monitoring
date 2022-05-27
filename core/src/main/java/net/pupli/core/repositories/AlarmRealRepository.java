package net.pupli.core.repositories;

import net.pupli.core.models.AlarmReal;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface AlarmRealRepository extends MongoRepository<AlarmReal, String> {
}
