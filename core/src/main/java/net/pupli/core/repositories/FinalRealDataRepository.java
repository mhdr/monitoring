package net.pupli.core.repositories;

import net.pupli.core.models.FinalRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalRealDataRepository extends MongoRepository<FinalRealData, String> {
}