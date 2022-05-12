package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alarms_history_real")
public class AlarmHistoryReal {
    @Id
    private String id;
}
