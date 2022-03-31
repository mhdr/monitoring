package net.pupli.core.libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFile {

    Logger logger = LoggerFactory.getLogger(ConfigFile.class);


    public String getMongodbHost() {
        return mongodbHost;
    }

    public String getMongodbUserName() {
        return mongodbUserName;
    }

    public String getMongodbPassword() {
        return mongodbPassword;
    }

    private String mongodbHost;
    private String mongodbUserName;
    private String mongodbPassword;

    public ConfigFile() {
        try {
            Path currentPath = Paths.get(System.getProperty("user.home"));
            Path filePath = Paths.get(currentPath.toString(), ".monitoringv5", "core.config");
            File file=filePath.toFile();

            if (file.exists()) {
                Properties prop = new Properties();

                FileInputStream fis = new FileInputStream(file);
                prop.load(fis);
                mongodbHost = prop.getProperty("mongodb.host");
                mongodbUserName = prop.getProperty("mongodb.username");
                mongodbPassword = prop.getProperty("mongodb.password");

                fis.close();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
