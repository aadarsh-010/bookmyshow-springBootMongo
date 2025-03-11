package com.bookmyshowspring.demo.persistence;

import com.bookmyshowspring.demo.models.Indexes.MovieIndex;
import com.bookmyshowspring.demo.models.Movie;
import com.bookmyshowspring.demo.repository.elastic.MovieElasticRepository;
import com.bookmyshowspring.demo.repository.mongo.MovieMongoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviePersistence implements IPersistence<Movie> {

    private final MovieMongoRepository movieMongoRepository;
    private final MovieElasticRepository movieElasticRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public MoviePersistence(MovieMongoRepository movieMongoRepository, MovieElasticRepository movieElasticRepository) {
        this.movieMongoRepository = movieMongoRepository;
        this.movieElasticRepository = movieElasticRepository;
    }

    @Override
    public Movie save(Movie movie) {
        Movie movieObject = movieMongoRepository.saveMovie(movie);

        MovieIndex movieIndex = modelMapper.map(movieObject, MovieIndex.class);
        movieElasticRepository.save(movieIndex);
        return movieObject;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return movieMongoRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        movieMongoRepository.deleteById(id);
        movieElasticRepository.deleteById(id);
    }
}
