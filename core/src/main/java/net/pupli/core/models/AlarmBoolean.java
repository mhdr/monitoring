package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since 2022-05-27
 */
@Document(collection = "alarms_boolean")
public class AlarmBoolean {

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

    public AlarmBoolean(int alarmType) {
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
        // 1 => equal
        // 2 => not equal
        private int compareType;
        private Integer value;
        // use this value for between compare type
        private Integer value2;
        private int delay;

        public CompareProperties(int compareType, Integer value, Integer value2, int delay) {
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

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getValue2() {
            return value2;
        }

        public void setValue2(Integer value2) {
            this.value2 = value2;
        }
    }

    public static class TimeoutProperties {
        // timeout value in seconds
        private int value;

        public TimeoutProperties() {
            this.value = 60;
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
