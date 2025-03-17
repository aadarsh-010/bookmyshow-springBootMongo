package com.bookmyshowspring.demo.services;
import com.bookmyshowspring.demo.models.MovieReview;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieReviewProducerService {

    private static final String TOPIC = "movie-reviews";

    private final KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;
    public MovieReviewProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper  = new ObjectMapper();
    }

    public void sendReview(MovieReview review) {
   try {
        String message = objectMapper.writeValueAsString(review);
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Message sent: " + message);
    } catch (Exception e) {
        System.err.println("Error sending review: " + e.getMessage());
    }
    }
}

