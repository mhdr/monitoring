package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("prev_real_data_saved")
// previous data we saved on items history
// we need this because we want to know when we should save new data on items history
public class PrevRealDataSaved implements Serializable {

    @Id
    // this is the itemId that we use it here as id
    private String id;
    private Double value;
    private DateTime time;

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
