package net.pupli.core.repositories;

import net.pupli.core.models.BooleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooleanDataRepository extends MongoRepository<BooleanData, String> {
}
