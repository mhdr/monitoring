package net.pupli.core.configs;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    /*
     * Use the standard Mongo driver API to create a com.mongodb.client.MongoClient instance.
     */
    public MongoClient mongoClient() {
        //String connectionString = "mongodb://monitoringv5:monitoringv5@localhost:27017/?authSource=admin&authMechanism=SCRAM-SHA-1";
        MongoCredential credential = MongoCredential.createScramSha256Credential("monitoringv5", "admin", "monitoringv5".toCharArray());
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
                        .credential(credential)
                        .build());
        return mongoClient;
    }

    @Override
    protected String getDatabaseName() {
        return "monitoringv5";
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("net.pupli");
    }
}
