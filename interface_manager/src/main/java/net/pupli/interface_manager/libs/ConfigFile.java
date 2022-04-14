package net.pupli.interface_manager.libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFile {

    private static ConfigFile INSTANCE;

    public static ConfigFile getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfigFile();
        }

        return INSTANCE;
    }

    Logger logger = LoggerFactory.getLogger(ConfigFile.class);
    private String rabbitmqHost;
    private String rabbitmqUserName;
    private String rabbitmqPassword;
    private String rabbitmqCoreHost;
    private String rabbitmqCoreUserName;
    private String rabbitmqCorePassword;
    private String coreWebAddress;

    public ConfigFile() {
        try {
            Path currentPath = Paths.get(System.getProperty("user.home"));
            Path filePath = Paths.get(currentPath.toString(), ".monitoring", "interface_manager.config");
            File file=filePath.toFile();

            if (file.exists()) {
                Properties prop = new Properties();

                FileInputStream fis = new FileInputStream(file);
                prop.load(fis);
                rabbitmqHost = prop.getProperty("rabbitmq.host");
                rabbitmqUserName = prop.getProperty("rabbitmq.username");
                rabbitmqPassword = prop.getProperty("rabbitmq.password");

                rabbitmqCoreHost = prop.getProperty("core.rabbitmq.host");
                rabbitmqCoreUserName = prop.getProperty("core.rabbitmq.username");
                rabbitmqCorePassword = prop.getProperty("core.rabbitmq.password");

                coreWebAddress=prop.getProperty("core.web.address");

                fis.close();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public String getCoreWebAddress() {
        return coreWebAddress;
    }

    public String getRabbitmqCoreHost() {
        return rabbitmqCoreHost;
    }

    public String getRabbitmqCoreUserName() {
        return rabbitmqCoreUserName;
    }

    public String getRabbitmqCorePassword() {
        return rabbitmqCorePassword;
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
