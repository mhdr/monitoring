package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import net.pupli.core.models.mongodb.MonitoringItem;

import java.util.HashMap;
import java.util.List;

public class StaticCache {
    private HashMap<Integer, String> itemIds;
    private HashMap<String, MonitoringItem> items;

    public StaticCache() {
        loadItems();
    }

    public HashMap<String, MonitoringItem> getItems() {
        return items;
    }

    private void loadItems() {

        itemIds = new HashMap<Integer, String>();
        items = new HashMap<String, MonitoringItem>();

        List<MonitoringItem> monitoringItems = MyContext.monitoringItemRepository.findAll();
        for (MonitoringItem item : monitoringItems) {
            itemIds.put(item.getItemId(), item.getId());
            items.put(item.getId(), item);
        }
    }

    public HashMap<Integer, String> getItemIds() {
        return itemIds;
    }
}
