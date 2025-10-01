package net.engineeringdigest.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document("config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigPojo {

    private String key;
    private String value;
}
