package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.JournalPOJO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalPOJO, ObjectId> {

}
