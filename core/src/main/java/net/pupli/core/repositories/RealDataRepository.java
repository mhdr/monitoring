package net.pupli.core.repositories;

import net.pupli.core.models.RealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealDataRepository extends MongoRepository<RealData, String> {
}