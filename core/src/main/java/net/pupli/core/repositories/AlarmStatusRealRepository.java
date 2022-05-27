package net.pupli.core.repositories;

import net.pupli.core.models.AlarmStatusReal;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @since 2022-05-27
 */
public interface AlarmStatusRealRepository extends MongoRepository<AlarmStatusReal, String> {
}
