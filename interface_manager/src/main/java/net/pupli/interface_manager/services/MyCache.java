package net.pupli.interface_manager.services;
;
import net.pupli.interface_manager.dto.ResponseItemsDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCache {
    private HashMap<Integer,ResponseItemsDto.Item> items;

    public MyCache() {
        items = new HashMap<>();
    }

    public HashMap<Integer, ResponseItemsDto.Item> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, ResponseItemsDto.Item> items) {
        this.items = items;
    }
}
