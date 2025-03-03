package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.user.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CreatorRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CreatorRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Creator saveCreator(Creator creator) {
       return  mongoTemplate.insert(creator);
    }


    public Optional<Creator> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Creator.class));
    }


    public List<Creator> findAll() {
        return mongoTemplate.findAll(Creator.class);
    }


    public void updateCreator(String id, Creator creator) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("name", creator.getName())
                .set("email", creator.getEmail());
        mongoTemplate.updateFirst(query, update, Creator.class);
    }


    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Creator.class);
    }
}
