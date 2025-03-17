package com.bookmyshowspring.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class MovieReview {
    @Id
    private String id;
    private String movieName;
    private String userName;
    private int rating;
    private String reviewText;

    public MovieReview() {}

    public MovieReview(String movieName, String userName, int rating, String reviewText) {
        this.movieName = movieName;
        this.userName = userName;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "movieName='" + movieName + '\'' +
                ", userName='" + userName + '\'' +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
