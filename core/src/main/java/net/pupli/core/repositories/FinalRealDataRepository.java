package net.pupli.core.repositories;

import net.pupli.core.models.FinalRealData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @since 2022-05-27
 */
@Repository
public interface FinalRealDataRepository extends MongoRepository<FinalRealData, String> {
}