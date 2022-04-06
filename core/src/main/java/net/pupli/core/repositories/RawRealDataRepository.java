package net.pupli.core.repositories;

import net.pupli.core.models.RawRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawRealDataRepository extends MongoRepository<RawRealData, String> {
}
