package net.pupli.core.repositories;

import net.pupli.core.models.FinalBooleanData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @since 2022-05-27
 */
@Repository
public interface FinalBooleanDataRepository extends MongoRepository<FinalBooleanData, String> {
}
