package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.ShowSeats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShowSeatRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShowSeatRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ShowSeats saveShowSeat(ShowSeats showSeat) {
        return mongoTemplate.insert(showSeat);
    }

    public Optional<ShowSeats> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, ShowSeats.class));
    }

    public List<ShowSeats> findAll() {
        return mongoTemplate.findAll(ShowSeats.class);
    }


    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, ShowSeats.class);
    }
}
