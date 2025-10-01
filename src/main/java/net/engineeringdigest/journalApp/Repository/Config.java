package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.ConfigPojo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Config extends MongoRepository<ConfigPojo, ObjectId> {
}
