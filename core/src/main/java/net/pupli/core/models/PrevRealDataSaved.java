package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "prev_real_data_saved")
// previous data we saved on items history
// we need this because we want to know when we should save new data on items history
public class PrevRealDataSaved {

    @Id
    // this is the itemId that we use it here as id
    private String id;

    @Indexed
    private String itemId;
    private Double value;
    private DateTime time;

    public PrevRealDataSaved() {
    }

    public PrevRealDataSaved(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
