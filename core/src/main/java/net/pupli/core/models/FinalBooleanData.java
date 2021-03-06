package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since 2022-05-27
 */
@Document(collection = "final_boolean_data")
public class FinalBooleanData {
    @Id
    private String id;
    @Indexed
    private String itemId;
    private Integer value;
    private DateTime time;

    public FinalBooleanData() {
    }

    public FinalBooleanData(String itemId) {
        this.itemId = itemId;
    }

    public FinalBooleanData(String itemId, Integer value, DateTime time) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }
}
