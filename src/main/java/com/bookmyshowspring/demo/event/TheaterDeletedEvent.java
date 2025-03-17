package com.bookmyshowspring.demo.event;

public class TheaterDeletedEvent {
    private final String userId;
    private final String theaterId;

    public TheaterDeletedEvent(String userId, String theaterId) {
        this.userId = userId;
        this.theaterId = theaterId;
    }

    public String getUserId() { return userId; }
    public String getTheaterId() { return theaterId; }
}
