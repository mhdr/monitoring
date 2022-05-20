package net.pupli.core.repositories;

import net.pupli.core.models.AlarmStatusReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmStatusRealRepository extends MongoRepository<AlarmStatusReal, String> {
}
