package net.pupli.web_server.repositories;


import net.pupli.web_server.models.PrevBooleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevBooleanDataRepository extends MongoRepository<PrevBooleanData, String> {
}
