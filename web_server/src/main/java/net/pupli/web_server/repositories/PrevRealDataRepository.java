package net.pupli.web_server.repositories;

import net.pupli.web_server.models.PrevRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevRealDataRepository extends MongoRepository<PrevRealData, String> {
}
