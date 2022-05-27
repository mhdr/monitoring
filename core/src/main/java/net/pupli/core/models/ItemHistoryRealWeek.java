package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since 2022-05-27
 */
@Document(collection = "items_history_real_week")
// use this collection for caching items for a week
// first sort by itemId then sort by time, sort by item is descending because it's better to have recent data on top
@CompoundIndexes({@CompoundIndex(name = "itemId_time", def = "{'itemId' : 1, 'time' : -1}")})
public class ItemHistoryRealWeek {
    @Id
    private String id;
    private String itemId;
    private Double value;
    private DateTime time;

    public ItemHistoryRealWeek(String itemId, Double value, DateTime time) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
    }

    public ItemHistoryRealWeek() {
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
