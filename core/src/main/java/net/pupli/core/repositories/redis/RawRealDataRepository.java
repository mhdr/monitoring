package net.pupli.core.repositories.redis;

import net.pupli.core.models.redis.RawRealData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawRealDataRepository extends CrudRepository<RawRealData, String> {
}
