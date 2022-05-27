package net.pupli.core.services;

import net.pupli.core.configs.AppConfig;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.AlarmBoolean;
import net.pupli.core.models.AlarmReal;
import net.pupli.core.models.InterfaceCredential;
import net.pupli.core.models.MonitoringItem;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public MyCache myCache;

    public Test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        myCache = context.getBean(MyCache.class);
    }

    public void insertAlarmBoolean() {
        AlarmBoolean alarmBoolean1 = new AlarmBoolean(1);
        alarmBoolean1.setItemId("624dec29b0cda5494a8eba8c");
        alarmBoolean1.setMessage("Data has Alarm");
        AlarmBoolean.CompareProperties compareProperties = new AlarmBoolean.CompareProperties(1, 1, null, 10);
        alarmBoolean1.setCompareProps(compareProperties);

        AlarmBoolean alarmBoolean2 = new AlarmBoolean(2);
        alarmBoolean2.setItemId("624dec29b0cda5494a8eba8c");
        alarmBoolean2.setMessage("Timeout reading");
        AlarmBoolean.TimeoutProperties timeoutProperties = new AlarmBoolean.TimeoutProperties(60);
        alarmBoolean2.setTimeoutProps(timeoutProperties);

        MyContext.alarmBooleanRepository.insert(alarmBoolean1);
        MyContext.alarmBooleanRepository.insert(alarmBoolean2);
    }

    public void insertAlarmReal() {
        AlarmReal alarmReal1 = new AlarmReal(1);
        alarmReal1.setItemId("6245c866996098751ac96b46");
        alarmReal1.setMessage("CPU Usage is High");
        AlarmReal.CompareProperties compareProperties = new AlarmReal.CompareProperties(2, 50.0, null, 30);
        alarmReal1.setCompareProps(compareProperties);

        AlarmReal alarmReal2 = new AlarmReal(2);
        alarmReal2.setItemId("6245c866996098751ac96b46");
        alarmReal2.setMessage("Timeout reading CPU Usage");
        AlarmReal.TimeoutProperties timeoutProperties = new AlarmReal.TimeoutProperties(60);
        alarmReal2.setTimeoutProps(timeoutProperties);

        MyContext.alarmRealRepository.insert(alarmReal1);
        MyContext.alarmRealRepository.insert(alarmReal2);
    }

    public void InsertCredential() {
        MyContext.interfaceCredentialRepository.insert(new InterfaceCredential("779e43d0-7e31-49e5-864f-26234abf3909",
                DateTime.now().plusYears(1), false));

        MyContext.interfaceCredentialRepository.insert(new InterfaceCredential("66ad9345-9e09-4629-aed2-e8684a9cf31d",
                DateTime.now().plusYears(1), false));
    }

    public void InsertItems() {
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
