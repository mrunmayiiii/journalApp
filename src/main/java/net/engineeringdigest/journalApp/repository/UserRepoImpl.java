package net.engineeringdigest.journalApp.repository;


import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/// /////this is criteria for complex queries not from repo
///
@Repository
public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User>getUserForSA(){
//        Query query=new Query();
//       // query.addCriteria(Criteria.where("userName").is("madhura"));
//      // query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
//       // query.addCriteria(Criteria.where("email").ne(null).ne(""));
//
//
//        Criteria criteria = Criteria.where("email")
//                .regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
//                .nin(null, "");
//
//        query.addCriteria(criteria);
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
//
////        Criteria criteria=new Criteria();
////        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
////                Criteria.where("sentimentAnalysis").is(true))
////        );
//        List<User> users = mongoTemplate.find(query, User.class);
//        return users;
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, User.class);
    }
}
