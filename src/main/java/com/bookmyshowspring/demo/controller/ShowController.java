package com.bookmyshowspring.demo.controller;

import com.bookmyshowspring.demo.dto.ShowDTO;
import com.bookmyshowspring.demo.dto.ShowSeatsDTO;
import com.bookmyshowspring.demo.enums.SeatType;
import com.bookmyshowspring.demo.models.Show;
import com.bookmyshowspring.demo.services.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/create")
    public ResponseEntity<ShowDTO> createShow(@RequestBody ShowDTO showDTO, @RequestBody ShowSeatsDTO showSeatsDTO) {
        ShowDTO createdShow = showService.CreateShow(showDTO, showSeatsDTO);
        return ResponseEntity.ok(createdShow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable String id) {
        return ResponseEntity.ok(showService.getAvaiableShow(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShow(@PathVariable String id) {
        showService.DeleteShow(id);
        return ResponseEntity.ok("Show deleted successfully");
    }

    @GetMapping("/{id}/available-seats")
    public ResponseEntity<Integer> getAvailableSeats(@PathVariable String id, @RequestParam SeatType seatType) {
        int availableSeats = showService.getAvailableSeats(id, seatType);
        return ResponseEntity.ok(availableSeats);
    }
}
