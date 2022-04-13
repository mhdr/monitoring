package net.pupli.interface_manager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseItemsDto implements Serializable {

    private List<Item> items;

    public ResponseItemsDto() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item implements Serializable {


        private String id;
        private int itemId;
        private int qos;
        public Item() {
        }
        public Item(String id, int itemId, int qos) {
            this.id = id;
            this.itemId = itemId;
            this.qos = qos;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getQos() {
            return qos;
        }

        public void setQos(int qos) {
            this.qos = qos;
        }
    }
}
