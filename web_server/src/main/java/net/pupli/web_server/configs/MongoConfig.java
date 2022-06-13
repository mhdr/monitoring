package net.pupli.web_server.configs;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.pupli.web_server.libs.ConfigFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
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

        ConfigFile configFile = ConfigFile.getInstance();
        MongoCredential credential = MongoCredential.createScramSha256Credential(configFile.getMongodbUserName(), "admin", configFile.getMongodbPassword().toCharArray());
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress(configFile.getMongodbHost(), 27017))))
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

    @Override
    protected boolean autoIndexCreation() {
        return false;
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
