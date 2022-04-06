package net.pupli.core.models.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.Date;

@RedisHash("RawRealData")
public class RawRealData {
    @Id
    private String id;
    private int itemId;
    private Double value;
    private LocalDateTime time;

    public RawRealData() {
    }

    public RawRealData(String id, int itemId, Double value, LocalDateTime time) {
        this.id = id;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
