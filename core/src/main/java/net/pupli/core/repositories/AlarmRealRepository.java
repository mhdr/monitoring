package net.pupli.core.repositories;

import net.pupli.core.models.AlarmReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmRealRepository extends MongoRepository<AlarmReal, String> {
}
