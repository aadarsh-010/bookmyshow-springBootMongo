package com.bookmyshowspring.demo.controller;


import com.bookmyshowspring.demo.dto.BookingDTO;
import com.bookmyshowspring.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookShow(@RequestBody BookingDTO bookingDTO) {
        try {
            bookingService.bookShow(bookingDTO);
            return ResponseEntity.ok("Booking successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Booking failed: " + e.getMessage());
        }
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable String bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking cancelled successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cancellation failed: " + e.getMessage());
        }
    }
}
