package net.pupli.core.repositories;

import net.pupli.core.models.PrevRealDataSaved;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PrevRealDataSavedRepository extends MongoRepository<PrevRealDataSaved, String> {
    Optional<PrevRealDataSaved> findByItemId(String itemId);
}
