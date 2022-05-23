package net.pupli.core.repositories;

import net.pupli.core.models.AlarmBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmBooleanRepository extends MongoRepository<AlarmBoolean, String> {
}
