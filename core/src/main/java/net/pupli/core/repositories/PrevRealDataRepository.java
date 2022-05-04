package net.pupli.core.repositories;

import net.pupli.core.models.PrevRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface PrevRealDataRepository extends MongoRepository<PrevRealData, String> {
}
