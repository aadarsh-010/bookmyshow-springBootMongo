package com.bookmyshowspring.demo.repository.elastic;

import com.bookmyshowspring.demo.models.Indexes.MovieIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieElasticRepository {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public MovieElasticRepository(ElasticsearchRestTemplate elasticsearchTemplate, ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public MovieIndex save(MovieIndex movie) {
        return elasticsearchTemplate.save(movie);
    }

    public Optional<MovieIndex> findById(String id) {
        return Optional.ofNullable(elasticsearchTemplate.get(id, MovieIndex.class));
    }

    public void deleteById(String id) {
        elasticsearchTemplate.delete(id, MovieIndex.class);
    }

    public List<MovieIndex> searchMovie(String query) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .should(QueryBuilders.wildcardQuery("title", "*" + query.toLowerCase() + "*"))
                        .should(QueryBuilders.matchPhrasePrefixQuery("title", query))
                )
                .build();

        SearchHits<MovieIndex> searchHits = elasticsearchOperations.search(searchQuery, MovieIndex.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
