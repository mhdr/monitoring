package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @since 2022-05-27
 */
@Document(collection = "raw_boolean_data")
public class RawBooleanData {
    @Id
    private String id;
    private String itemId;
    private String value;
    private String time;

    public RawBooleanData() {
    }

    public RawBooleanData(String itemId, String value, String time) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
