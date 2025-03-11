package com.bookmyshowspring.demo.repository.mongo;

import com.bookmyshowspring.demo.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookingMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Booking saveBooking(Booking booking) {
        return mongoTemplate.save(booking);
    }


    public Optional<Booking> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Booking.class));
    }

    public List<Booking> findAll() {
        return mongoTemplate.findAll(Booking.class);
    }


    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Booking.class);
    }
}
