package net.pupli.core;

import net.pupli.core.libs.MyContext;
import net.pupli.core.services.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "net.pupli.core.repositories")
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);

        //Test test = new Test();
        //test.insertAlarmReal();
    }

}
