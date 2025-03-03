package com.bookmyshowspring.demo.controller;


import com.bookmyshowspring.demo.dto.MovieDTO;
import com.bookmyshowspring.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Create a new movie
    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        MovieDTO createdMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.ok(createdMovie);
    }

    // Delete a movie by ID
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable String movieId) {
        try {
            movieService.deleteMovie(movieId);
            return ResponseEntity.ok("Movie deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Add a show reference to a movie
    @PostMapping("/{movieId}/shows/{showId}")
    public ResponseEntity<String> addShowRefToMovie(@PathVariable String movieId, @PathVariable String showId) {
        movieService.addShowRefToMovie(showId, movieId);
        return ResponseEntity.ok("Show added to movie successfully");
    }

}
