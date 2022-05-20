package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.AlarmReal;
import net.pupli.core.models.MonitoringItem;
import net.pupli.core.models.RawBooleanData;
import net.pupli.core.models.RawRealData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCache {
    private HashMap<Integer, String> itemIds;
    private HashMap<String, MonitoringItem> items;
    private HashMap<String, RawBooleanData> rawBooleanData;
    private HashMap<String, RawRealData> rawRealData;
    // this flag indicates app should receive and save raw data
    private Boolean shouldProcessData = false;
    public Boolean getShouldProcessData() {
        return shouldProcessData;
    }

    public void setShouldProcessData(Boolean shouldProcessData) {
        this.shouldProcessData = shouldProcessData;
    }

    public HashMap<String, RawBooleanData> getRawBooleanData() {
        return rawBooleanData;
    }

    public HashMap<String, RawRealData> getRawRealData() {
        return rawRealData;
    }

    public HashMap<String, MonitoringItem> getItems() {
        return items;
    }

    public void loadItems() {

        itemIds = new HashMap<>();
        items = new HashMap<>();

        List<MonitoringItem> monitoringItems = MyContext.monitoringItemRepository.findAll();
        for (MonitoringItem item : monitoringItems) {
            itemIds.put(item.getItemId(), item.getId());
            items.put(item.getId(), item);
        }
    }

    public void loadRawItems() {

        rawBooleanData = new HashMap<>();
        rawRealData = new HashMap<>();

        List<RawBooleanData> booleanResult = new ArrayList<>();
        List<RawRealData> realResult = new ArrayList<>();

        items.forEach((s, monitoringItem) -> {
            if (monitoringItem.getItemType() == 1) {
                RawBooleanData booleanData = new RawBooleanData(monitoringItem.getId(), null, null);
                rawBooleanData.put(s, booleanData);
                booleanResult.add(booleanData);
            } else if (monitoringItem.getItemType() == 2) {
                RawRealData realData = new RawRealData(monitoringItem.getId(), null, null);
                rawRealData.put(s, realData);
                realResult.add(realData);
            }
        });


        MyContext.rawBooleanDataRepository.saveAll(booleanResult);
        MyContext.rawRealDataRepository.saveAll(realResult);
    }

    public HashMap<Integer, String> getItemIds() {
        return itemIds;
    }
}
