package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since 2022-05-27
 */
@Document(collection = "alarms_history_boolean")
@CompoundIndexes({@CompoundIndex(name = "alarmId_partitionKey_time", def = "{'alarmId' : 1, partitionKey : -1, 'time' : -1}")})
public class AlarmHistoryBoolean {

    @Id
    private String id;
    private String alarmId;
    private String partitionKey;
    private boolean hasAlarm;
    private DateTime time;
    public AlarmHistoryBoolean(String alarmId, boolean hasAlarm, DateTime time) {
        this.alarmId = alarmId;
        this.hasAlarm = hasAlarm;
        this.time = time;
        this.partitionKey = String.format("%d%02d", time.year().get(), time.monthOfYear().get());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public boolean isHasAlarm() {
        return hasAlarm;
    }

    public void setHasAlarm(boolean hasAlarm) {
        this.hasAlarm = hasAlarm;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

}
