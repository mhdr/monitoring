package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items_history_boolean_week")
// use this collection for caching items for a week
// first sort by itemId then sort by time, sort by item is descending because it's better to have recent data on top
@CompoundIndexes({@CompoundIndex(name = "itemId_time", def = "{'itemId' : 1, 'time' : -1}")})
public class ItemHistoryBooleanWeek {
    @Id
    private String id;
    private String itemId;
    private Integer value;
    private DateTime time;

    public ItemHistoryBooleanWeek() {
    }

    public ItemHistoryBooleanWeek(String itemId, Integer value, DateTime time) {
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
