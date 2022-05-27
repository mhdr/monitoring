package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since 2022-05-27
 */
@Document(collection = "alarms_status_real")
public class AlarmStatusReal {
    @Id
    private String id;
    @Indexed
    private String alarmId;
    private boolean isSuspicious;
    private DateTime suspiciousTime;
    private boolean hasAlarm;
    private DateTime alarmTime;

    public AlarmStatusReal(String alarmId) {
        this.alarmId = alarmId;
        this.isSuspicious = false;
        this.suspiciousTime = null;
        this.hasAlarm = false;
        this.alarmTime = null;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuspicious() {
        return isSuspicious;
    }

    public void setSuspicious(boolean suspicious) {
        isSuspicious = suspicious;
    }

    public DateTime getSuspiciousTime() {
        return suspiciousTime;
    }

    public void setSuspiciousTime(DateTime suspiciousTime) {
        this.suspiciousTime = suspiciousTime;
    }

    public boolean isHasAlarm() {
        return hasAlarm;
    }

    public void setHasAlarm(boolean hasAlarm) {
        this.hasAlarm = hasAlarm;
    }

    public DateTime getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(DateTime alarmTime) {
        this.alarmTime = alarmTime;
    }
}
