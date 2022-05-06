package net.pupli.core.repositories;

import net.pupli.core.models.PrevBooleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevBooleanDataRepository extends MongoRepository<PrevBooleanData, String> {
}
