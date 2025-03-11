package com.bookmyshowspring.demo.controller;

import com.bookmyshowspring.demo.models.Indexes.MovieIndex;
import com.bookmyshowspring.demo.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/movies")
    public List<MovieIndex> searchMovies(@RequestParam String query) {
        return searchService.searchMovie(query);
    }
}
