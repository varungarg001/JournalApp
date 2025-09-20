package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.JournalPOJO;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepo extends MongoRepository<Users, ObjectId> {
    Users findByUserName(String username);
}
