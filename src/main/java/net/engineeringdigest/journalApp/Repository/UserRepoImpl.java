package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> getUserSA(){
        Query query=new Query();
//        Criteria criteria=new Criteria();
//        criteria.orOperator(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"),Criteria.where("sentimentAnalysis").is("true"));//for or condition
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<Users> users = mongoTemplate.find(query, Users.class);
        return users;
    }
}
