package net.pupli.core;

import net.pupli.core.services.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
        Test test=new Test();
        //test.Insert();
        System.out.println(test.staticCache.getItemIds().size());
    }

}
