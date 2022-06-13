package net.pupli.web_server.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "final_real_data")
public class FinalRealData {

    @Id
    private String id;
    @Indexed
    private String itemId;
    private Double value;
    private DateTime time;

    public FinalRealData(String itemId) {
        this.itemId = itemId;
    }

    public FinalRealData() {
    }

    public FinalRealData(String itemId, Double value, DateTime time) {
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
