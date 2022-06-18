package net.pupli.web_server.repositories;

import net.pupli.web_server.models.RawRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawRealDataRepository extends MongoRepository<RawRealData, String> {
}
