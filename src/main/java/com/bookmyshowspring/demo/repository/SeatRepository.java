package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SeatRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SeatRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Seat saveSeat(Seat seat) {
        return mongoTemplate.insert(seat);
    }
    public void saveAllSeats(List<Seat> seats) {
        mongoTemplate.insertAll(seats);
    }


    public Optional<Seat> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Seat.class));
    }

    public List<Seat> findAllByIds(List<String> seatRefs) {
        Query query = new Query(Criteria.where("id").in(seatRefs));
        return mongoTemplate.find(query, Seat.class);
    }


    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Seat.class);
    }
}
