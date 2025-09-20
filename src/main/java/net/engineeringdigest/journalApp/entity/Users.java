package net.engineeringdigest.journalApp.entity;


//import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    private ObjectId id;

    @Indexed(unique = true)          //to make the usernamw unique
    @NonNull
    private String userName;
    @NonNull
    private String passWord;
    private String email;
    private boolean sentimentAnalysis;

    @DBRef
    private List<JournalPOJO> journalPOJOList=new ArrayList<>();
    private List<String>roles;

}
