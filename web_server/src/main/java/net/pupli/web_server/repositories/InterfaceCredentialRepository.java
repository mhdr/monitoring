package net.pupli.web_server.repositories;

import net.pupli.web_server.models.InterfaceCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceCredentialRepository extends MongoRepository<InterfaceCredential, String> {
}
