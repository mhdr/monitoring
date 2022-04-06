package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "raw_real_data")
public class RawRealData {
    @Id
    private String id;
    private String itemId;
    private Double value;
    private LocalDateTime time;

    public RawRealData() {
    }

    public RawRealData(String itemId, Double value, LocalDateTime time) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
