package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TheatreRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TheatreRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Theatre saveTheatre(Theatre theatre) {
        return mongoTemplate.insert(theatre);
    }

    public Optional<Theatre> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Theatre.class));
    }

    public List<Theatre> findAll() {
        return mongoTemplate.findAll(Theatre.class);
    }

    public void updateTheatre(String id, Theatre theatre) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("name", theatre.getName())
                .set("location", theatre.getLocation())
                .set("ownerId", theatre.getOwnerId());
        mongoTemplate.updateFirst(query, update, Theatre.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Theatre.class);
    }
}
