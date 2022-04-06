package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
public class MonitoringItem {

    @Id
    private String id;
    @Indexed
    private int itemId;
    private String itemName;
    private boolean isDisabled;
    private boolean isVisible;
    private int interval;
    private int boxPlotSize;
    /**
     * 0 => no qos
     * 1 => qos level 1
     */
    private int qos;
    /**
     * 1 => Boolean
     * 2 => Real
     */
    private int itemType;
    private BooleanItemProperties booleanProperties;
    private RealItemProperties realProperties;
    public MonitoringItem(int itemId, String itemName, int itemType, RealItemProperties realProperties) {
        this();
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.realProperties = realProperties;
    }
    public MonitoringItem(int itemId, String itemName, int itemType, BooleanItemProperties booleanProperties) {
        this();
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.booleanProperties = booleanProperties;
    }
    public MonitoringItem(int itemId, String itemName, int itemType) {
        this();
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public MonitoringItem() {
        this.itemId = -1;
        this.itemName = "";
        this.isDisabled = false;
        this.isVisible = true;
        this.interval = 60;
        this.boxPlotSize = -1;
        this.qos = 0;
        this.itemType = 1;
        booleanProperties = null;
        realProperties = null;
    }

    public BooleanItemProperties getBooleanProperties() {
        return booleanProperties;
    }

    public void setBooleanProperties(BooleanItemProperties booleanProperties) {
        this.booleanProperties = booleanProperties;
    }

    public RealItemProperties getRealProperties() {
        return realProperties;
    }

    public void setRealProperties(RealItemProperties realProperties) {
        this.realProperties = realProperties;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getBoxPlotSize() {
        return boxPlotSize;
    }

    public void setBoxPlotSize(int boxPlotSize) {
        this.boxPlotSize = boxPlotSize;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public static class BooleanItemProperties {
        private String onText;
        private String offText;

        public BooleanItemProperties() {

        }

        public BooleanItemProperties(String onText, String offText) {
            this.onText = onText;
            this.offText = offText;
        }


        public String getOnText() {
            return onText;
        }

        public void setOnText(String onText) {
            this.onText = onText;
        }
    }

    public static class RealItemProperties {
        private String unitName;

        public RealItemProperties() {
        }

        public RealItemProperties(String unitName) {
            this.unitName = unitName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
    }
}
