package com.bookmyshowspring.demo.services;


import com.bookmyshowspring.demo.models.Indexes.MovieIndex;
import com.bookmyshowspring.demo.models.user.Customer;
import com.bookmyshowspring.demo.repository.elastic.MovieElasticRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {


    MovieElasticRepository movieElasticRepository;

    public SearchService(MovieElasticRepository movieElasticRepository) {
        this.movieElasticRepository = movieElasticRepository;
    }

    public List<MovieIndex> searchMovie(String query) {
        return movieElasticRepository.searchMovie(query);
    }

}
