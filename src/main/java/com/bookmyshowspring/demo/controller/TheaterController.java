package com.bookmyshowspring.demo.controller;

import com.bookmyshowspring.demo.dto.TheatreDTO;
import com.bookmyshowspring.demo.models.Theatre;
import com.bookmyshowspring.demo.services.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping
    public ResponseEntity<String> createTheater(@RequestBody TheatreDTO theatreDTO) {
        System.out.println("asff");
        try {
            System.out.println("asff");
            theaterService.CreateTheater(theatreDTO);
            return ResponseEntity.ok("Theater created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{theaterId}")
    public ResponseEntity<String> deleteTheater(@PathVariable String theaterId) {
        try {
            theaterService.deleteTheater(theaterId);
            return ResponseEntity.ok("Theater deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity<Theatre> getTheater(@PathVariable String theaterId) {
        try {
            Theatre theatre = theaterService.getTheatreEvent(theaterId);
            return ResponseEntity.ok(theatre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
