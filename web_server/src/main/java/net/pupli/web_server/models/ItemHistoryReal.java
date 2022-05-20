package net.pupli.web_server.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items_history_real")
// first sort by itemId then sort by time, sort by item is descending because it's better to have recent data on top
@CompoundIndexes({@CompoundIndex(name = "itemId_partitionKey_time", def = "{'itemId' : 1, partitionKey : -1, 'time' : -1}")})
public class ItemHistoryReal {
    @Id
    private String id;
    private String itemId;
    private String partitionKey;
    private Double value;
    private DateTime time;

    public ItemHistoryReal(String itemId, Double value, DateTime time) {
        this.itemId = itemId;
        this.value = value;
        this.time = time;
        this.partitionKey = String.format("%d%02d", time.year().get(), time.monthOfYear().get());
    }

    public ItemHistoryReal() {
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

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
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
