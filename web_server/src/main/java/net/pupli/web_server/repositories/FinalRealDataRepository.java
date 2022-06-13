package net.pupli.web_server.repositories;

import net.pupli.web_server.models.FinalRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalRealDataRepository extends MongoRepository<FinalRealData, String> {
}