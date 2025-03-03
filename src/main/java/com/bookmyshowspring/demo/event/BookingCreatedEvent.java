package com.bookmyshowspring.demo.event;

public class BookingCreatedEvent {

    private final String userId;
    private final String bookingId;

    public BookingCreatedEvent(String userId, String bookingId) {
        this.userId = userId;
        this.bookingId = bookingId;
    }

    public String getUserId() { return userId; }
    public String getBookingId() { return bookingId; }
}
