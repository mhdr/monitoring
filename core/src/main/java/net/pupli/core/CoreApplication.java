package net.pupli.core;

import net.pupli.core.libs.DB;
import net.pupli.core.models.MonitoringItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);

        MonitoringItem monitoringItem=new MonitoringItem();
        monitoringItem.setItemId(1);
        monitoringItem.setItemName("CPU Usage");
        monitoringItem.setItemType(2);
        DB.monitoringItemRepository.save(monitoringItem);
    }

}
