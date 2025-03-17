package com.bookmyshowspring.demo.repository.mongo;

import com.bookmyshowspring.demo.models.Screen;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScreenMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ScreenMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Screen saveScreen(Screen screen) {
        return mongoTemplate.save(screen);
    }



    public Optional<Screen> findById(String id) {
        try {
            Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
            System.out.println("Searching for Screen with ID: " + id);

            Optional<Screen> result = Optional.ofNullable(mongoTemplate.findOne(query, Screen.class));

            result.ifPresentOrElse(
                    screen -> System.out.println("Screen found: " + screen),
                    () -> System.out.println("No Screen found with ID: " + id)
            );

            return result;
        } catch (Exception e) {
            System.err.println("Error fetching screen: " + e.getMessage());
            return Optional.empty();
        }
    }


    public List<Screen> findAll() {
        return mongoTemplate.findAll(Screen.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Screen.class);
    }
}
