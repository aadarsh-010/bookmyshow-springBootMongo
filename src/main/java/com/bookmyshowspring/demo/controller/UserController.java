package com.bookmyshowspring.demo.controller;

import com.bookmyshowspring.demo.dto.user.UserDTO;
import com.bookmyshowspring.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        System.out.println("heelooo");
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/theatres")
    public ResponseEntity<?> getOwnedTheatres(@PathVariable String id) {
        try {
            List<String> theatres = userService.getOwnedTheatres(id);
            return ResponseEntity.ok(theatres);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/bookings")
    public ResponseEntity<?> getBookings(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getBookings(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/bookings/{bookingId}")
    public ResponseEntity<String> removeUserBookings(@PathVariable String userId, @PathVariable String bookingId) {
        try {
            userService.removeUserBookings(userId, bookingId);
            return ResponseEntity.ok("Booking removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
