package net.pupli.web_server.libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFile {

    private static ConfigFile INSTANCE;
    Logger logger = LoggerFactory.getLogger(ConfigFile.class);
    private String mongodbHost;
    private String mongodbUserName;
    private String mongodbPassword;
    private String rabbitmqHost;
    private String rabbitmqUserName;
    private String rabbitmqPassword;
    private String redisHost;
    private String redisPassword;

    public ConfigFile() {
        try {
            Path currentPath = Paths.get(System.getProperty("user.home"));
            Path filePath = Paths.get(currentPath.toString(), ".monitoringv5", "web_server.config");
            File file = filePath.toFile();

            if (file.exists()) {
                Properties prop = new Properties();

                FileInputStream fis = new FileInputStream(file);
                prop.load(fis);
                mongodbHost = prop.getProperty("mongodb.host");
                mongodbUserName = prop.getProperty("mongodb.username");
                mongodbPassword = prop.getProperty("mongodb.password");

                rabbitmqHost = prop.getProperty("rabbitmq.host");
                rabbitmqUserName = prop.getProperty("rabbitmq.username");
                rabbitmqPassword = prop.getProperty("rabbitmq.password");

                redisHost = prop.getProperty("redis.host");
                redisPassword = prop.getProperty("redis.password");

                fis.close();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static ConfigFile getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfigFile();
        }

        return INSTANCE;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public String getMongodbHost() {
        return mongodbHost;
    }

    public String getMongodbUserName() {
        return mongodbUserName;
    }

    public String getMongodbPassword() {
        return mongodbPassword;
    }

    public String getRabbitmqHost() {
        return rabbitmqHost;
    }

    public String getRabbitmqUserName() {
        return rabbitmqUserName;
    }

    public String getRabbitmqPassword() {
        return rabbitmqPassword;
    }
}
