package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MovieRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Movie saveMovie(Movie movie) {
        return mongoTemplate.insert(movie);
    }

    public Optional<Movie> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Movie.class));
    }

    public List<Movie> findAll() {
        return mongoTemplate.findAll(Movie.class);
    }

    public List<Movie> findByTitleContainingIgnoreCase(String title) {
        Query query = new Query(Criteria.where("title").regex(title, "i"));
        return mongoTemplate.find(query, Movie.class);
    }

    public List<Movie> findByGenreContainingIgnoreCase(String genre) {
        Query query = new Query(Criteria.where("genre").regex(genre, "i"));
        return mongoTemplate.find(query, Movie.class);
    }

    public List<Movie> findByCastContainingIgnoreCase(String castMember) {
        Query query = new Query(Criteria.where("cast").regex(castMember, "i"));
        return mongoTemplate.find(query, Movie.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Movie.class);
    }
}
