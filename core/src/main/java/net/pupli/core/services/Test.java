package net.pupli.core.services;

import net.pupli.core.configs.AppConfig;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.MonitoringItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public MyCache myCache;

    public Test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        myCache = context.getBean(MyCache.class);
    }

    public void Insert() {
        MyContext.monitoringItemRepository.insert(new MonitoringItem(10, "Real Data 10", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(11, "Real Data 11", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(12, "Real Data 12", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(13, "Real Data 13", 2,
                new MonitoringItem.RealItemProperties("s")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(14, "Real Data 14", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(15, "Real Data 15", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(16, "Real Data 16", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(17, "Real Data 17", 2,
                new MonitoringItem.RealItemProperties("s")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(18, "Real Data 18", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(19, "Real Data 19", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(20, "Real Data 20", 2,
                new MonitoringItem.RealItemProperties("%")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(21, "Real Data 21", 2,
                new MonitoringItem.RealItemProperties("s")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(22, "Real Data 22", 2,
                new MonitoringItem.RealItemProperties("s")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(23, "Boolean Data 23", 1,
                new MonitoringItem.BooleanItemProperties("On", "Off")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(24, "Boolean Data 24", 1,
                new MonitoringItem.BooleanItemProperties("On", "Off")));

        MyContext.monitoringItemRepository.insert(new MonitoringItem(25, "Real Data 25", 2,
                new MonitoringItem.RealItemProperties("s")));
    }
}
