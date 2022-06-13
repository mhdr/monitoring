package net.pupli.web_server.repositories;

import net.pupli.web_server.models.FinalBooleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalBooleanDataRepository extends MongoRepository<FinalBooleanData, String> {
}
