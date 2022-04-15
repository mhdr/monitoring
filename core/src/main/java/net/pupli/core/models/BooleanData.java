package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boolean_data")
public class BooleanData {
    @Id
    private String id;
    @Indexed
    private String itemId;
    private Boolean value;
    private DateTime time;
    private Boolean prevValue;
    private DateTime prevTime;

    public BooleanData() {
    }

    public BooleanData(String itemId) {
        this.itemId = itemId;
    }

    public BooleanData(String itemId, Boolean value, DateTime time, Boolean prevValue, DateTime prevTime) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
        this.prevValue = prevValue;
        this.prevTime = prevTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Boolean getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(Boolean prevValue) {
        this.prevValue = prevValue;
    }

    public DateTime getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(DateTime prevTime) {
        this.prevTime = prevTime;
    }
}
