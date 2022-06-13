package net.pupli.web_server.repositories;

import net.pupli.web_server.models.RawBooleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawBooleanDataRepository extends MongoRepository<RawBooleanData, String> {
}
