package net.pupli.web_server.repositories;

import net.pupli.web_server.models.AlarmReal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmRealRepository extends MongoRepository<AlarmReal, String> {
}
