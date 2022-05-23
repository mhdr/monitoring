package net.pupli.core.repositories;

import net.pupli.core.models.AlarmStatusBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmStatusBooleanRepository extends MongoRepository<AlarmStatusBoolean,String> {
}
