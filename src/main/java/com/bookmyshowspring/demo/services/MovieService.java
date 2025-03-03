package com.bookmyshowspring.demo.services;


import com.bookmyshowspring.demo.dto.MovieDTO;
import com.bookmyshowspring.demo.models.Movie;
import com.bookmyshowspring.demo.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movierepo;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public MovieService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public MovieDTO createMovie(MovieDTO m1) {
        Movie movie = modelMapper.map(m1, Movie.class);
        movierepo.saveMovie(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    public void deleteMovie(String movieId) throws Exception {
        Optional<Movie> movieOptional = movierepo.findById(movieId);
        if (movieOptional.isEmpty()) {throw new Exception("INVALID Movie ID");}
        Movie movie = movieOptional.get();
        movierepo.deleteById(movie.getId());
    }

    public void addShowRefToMovie(String showid , String movieid){
        Movie movie = movierepo.findById(movieid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found"));
        movie.getShowsRunningThisMovie().add(showid);
        movierepo.saveMovie(movie);
    }
}
