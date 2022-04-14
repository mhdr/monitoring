package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "raw_boolean_data")
public class RawBooleanData {
    @Id
    private String id;
    private String itemId;
    private Boolean value;
    private DateTime time;

    public RawBooleanData() {
    }

    public RawBooleanData(String itemId, Boolean value, DateTime time) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
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

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }
}
