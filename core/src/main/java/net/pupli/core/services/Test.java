package net.pupli.core.services;

import net.pupli.core.configs.AppConfig;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.mongodb.MonitoringItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public StaticCache staticCache;

    public Test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        staticCache = context.getBean(StaticCache.class);
    }

    public void Insert() {
        MyContext.monitoringItemRepository.insert(new MonitoringItem(2, "Memory Usage", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(3, "Swap Usage", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(4, "Disk Usage", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(5, "System Uptime", 2,
                new MonitoringItem.RealItemProperties("s")));
    }
}
