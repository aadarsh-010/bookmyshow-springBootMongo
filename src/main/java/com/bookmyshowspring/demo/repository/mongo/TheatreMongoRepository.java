package com.bookmyshowspring.demo.repository.mongo;

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
public class TheatreMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TheatreMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Theatre saveTheatre(Theatre theatre) {
        return mongoTemplate.save(theatre);
    }

    public Optional<Theatre> findById(String id) {
       try {
           Query query = new Query(Criteria.where("_id").is(id));
           System.out.println(id);
           Optional<Theatre> x = Optional.ofNullable(mongoTemplate.findOne(query, Theatre.class));
           System.out.println("sss");
           if (x.equals(Optional.empty())) System.out.println("iasdf");
           System.out.println(x.get().getName() + "j");
           return x;
       }
        catch (Exception e) {
            System.err.println("Error fetching screen: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<Theatre> findAll() {
        return mongoTemplate.findAll(Theatre.class);
    }

    public void updateTheatre(String id, Theatre theatre) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update()
                .set("name", theatre.getName())
                .set("location", theatre.getLocation())
                .set("ownerId", theatre.getOwnerId());
        mongoTemplate.updateFirst(query, update, Theatre.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Theatre.class);
    }
}
