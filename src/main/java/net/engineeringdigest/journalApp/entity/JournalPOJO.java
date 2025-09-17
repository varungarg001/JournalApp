package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;



@Document(collection = "JournalPOJO")
@Data
@NoArgsConstructor
public class JournalPOJO {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
