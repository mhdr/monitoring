package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "real_data")
public class RealData {

    @Id
    private String id;
    @Indexed
    private String itemId;
    private Double value;
    private DateTime time;
    private Double prevValue;
    private DateTime prevTime;

    public RealData(String itemId) {
        this.itemId = itemId;
    }

    public RealData() {
    }

    public RealData(String itemId, Double value, DateTime time, Double prevValue, DateTime prevTime) {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Double getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(Double prevValue) {
        this.prevValue = prevValue;
    }

    public DateTime getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(DateTime prevTime) {
        this.prevTime = prevTime;
    }
}
