package net.pupli.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alarms_real")
public class AlarmReal {
    @Id
    private String id;
}
