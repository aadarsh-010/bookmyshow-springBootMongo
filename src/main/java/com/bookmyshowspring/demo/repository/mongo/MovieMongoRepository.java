package com.bookmyshowspring.demo.repository.mongo;

import com.bookmyshowspring.demo.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MovieMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MovieMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Movie saveMovie(Movie movie) {
        return mongoTemplate.save(movie);
    }

    public Optional<Movie> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Movie.class));
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Movie.class);
    }


}
