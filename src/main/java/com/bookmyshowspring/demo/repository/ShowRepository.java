package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShowRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShowRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Show saveShow(Show show) {
        return mongoTemplate.insert(show);
    }

    public Optional<Show> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Show.class));
    }

    public List<Show> findAll() {
        return mongoTemplate.findAll(Show.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Show.class);
    }
}
