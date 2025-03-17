package com.bookmyshowspring.demo.repository.mongo;

import com.bookmyshowspring.demo.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SeatMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SeatMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Seat saveSeat(Seat seat) {
        return mongoTemplate.save(seat);
    }
    public void saveAllSeats(List<Seat> seats) {
        mongoTemplate.insertAll(seats);
    }


    public Optional<Seat> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Seat.class));
    }

    public List<Seat> findAllByIds(List<String> seatRefs) {
        Query query = new Query(Criteria.where("_id").in(seatRefs));
        return mongoTemplate.find(query, Seat.class);
    }


    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Seat.class);
    }
}
