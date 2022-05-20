package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alarms_real")
public class AlarmReal {
    @Id
    private String id;
    @Indexed
    private String itemId;
    // 1 => compare values
    // 2 => timeout values
    private int alarmType;
    private boolean isDisabled;
    private int delay;
    private String message;
    private CompareProperties compareProps;
    private TimeoutProperties timeoutProps;
    private Buzzer[] buzzers;

    public AlarmReal(int alarmType) {
        this.alarmType = alarmType;

        // alarms are enabled by default
        this.isDisabled = false;

        // 10 seconds delay
        this.delay = 10;

        // empty message
        this.message = "";

        if (alarmType == 1) {
            this.compareProps = new CompareProperties();
        } else if (alarmType == 2) {
            this.timeoutProps = new TimeoutProperties();
        }

        // initialize buzzers
        buzzers = new Buzzer[]{};
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

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CompareProperties getCompareProps() {
        return compareProps;
    }

    public void setCompareProps(CompareProperties compareProps) {
        this.compareProps = compareProps;
    }

    public TimeoutProperties getTimeoutProps() {
        return timeoutProps;
    }

    public void setTimeoutProps(TimeoutProperties timeoutProps) {
        this.timeoutProps = timeoutProps;
    }

    public Buzzer[] getBuzzers() {
        return buzzers;
    }

    public void setBuzzers(Buzzer[] buzzers) {
        this.buzzers = buzzers;
    }

    public static class CompareProperties {
        // 1 => lower
        // 2 => higher
        // 3 => between
        private int compareType;
        private Double value;
        // use this value for between compare type
        private Double value2;
    }

    public static class TimeoutProperties {
        // timeout value in seconds
        private long value;
    }

    public static class Buzzer {
        private String name;
        private boolean trigger;
        private boolean isSilent;
        private int delay;
    }
}
