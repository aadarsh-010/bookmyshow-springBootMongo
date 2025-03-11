package com.bookmyshowspring.demo.repository.mongo;

import com.bookmyshowspring.demo.models.ScreenSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScreenSeatMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ScreenSeatMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ScreenSeat saveScreenSeat(ScreenSeat screenSeat) {
        return mongoTemplate.save(screenSeat);
    }

    public Optional<ScreenSeat> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, ScreenSeat.class));
    }

    public List<ScreenSeat> findAll() {
        return mongoTemplate.findAll(ScreenSeat.class);
    }


    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, ScreenSeat.class);
    }
}
