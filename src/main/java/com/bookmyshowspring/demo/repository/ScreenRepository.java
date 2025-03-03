package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScreenRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ScreenRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Screen saveScreen(Screen screen) {
        return mongoTemplate.insert(screen);
    }

    public Optional<Screen> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Screen.class));
    }

    public List<Screen> findAll() {
        return mongoTemplate.findAll(Screen.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Screen.class);
    }
}
