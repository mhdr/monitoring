package net.pupli.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadValueDto implements Serializable {

    private String credential;
    private String time;
    private List<Value> valueList;

    public ReadValueDto() {
        this.valueList = new ArrayList<>();
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Value> getValueList() {
        return valueList;
    }

    public void setValueList(List<Value> valueList) {
        this.valueList = valueList;
    }

    public static class Value implements Serializable {
        private int id;
        private String value;

        public Value(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}