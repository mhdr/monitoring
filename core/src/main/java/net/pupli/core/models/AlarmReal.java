package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since 2022-05-27
 */
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
    private String message;
    private CompareProperties compareProps;
    private TimeoutProperties timeoutProps;
    private Buzzer[] buzzers;

    public AlarmReal(int alarmType) {
        this.alarmType = alarmType;

        // alarms are enabled by default
        this.isDisabled = false;

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
        private int delay;

        public CompareProperties(int compareType, Double value, Double value2, int delay) {
            this.compareType = compareType;
            this.value = value;
            this.value2 = value2;
            this.delay = delay;
        }

        public CompareProperties() {
            this.delay = 10;
        }

        public int getCompareType() {
            return compareType;
        }

        public void setCompareType(int compareType) {
            this.compareType = compareType;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Double getValue2() {
            return value2;
        }

        public void setValue2(Double value2) {
            this.value2 = value2;
        }
    }

    public static class TimeoutProperties {
        // timeout value in seconds
        private int value;

        public TimeoutProperties() {
        }

        public TimeoutProperties(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class Buzzer {
        private String name;
        private boolean trigger;
        private boolean isSilent;
        private int delay;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isTrigger() {
            return trigger;
        }

        public void setTrigger(boolean trigger) {
            this.trigger = trigger;
        }

        public boolean isSilent() {
            return isSilent;
        }

        public void setSilent(boolean silent) {
            isSilent = silent;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }
    }
}
