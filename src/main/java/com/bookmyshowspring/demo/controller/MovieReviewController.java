package com.bookmyshowspring.demo.controller;

import com.bookmyshowspring.demo.models.MovieReview;
import com.bookmyshowspring.demo.services.MovieReviewProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class MovieReviewController {
    private final MovieReviewProducerService producer;

    public MovieReviewController(MovieReviewProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitReview(@RequestBody MovieReview review) {

        System.out.println("asf");
        producer.sendReview(review);
        return ResponseEntity.ok("Review submitted successfully!");
    }
}
