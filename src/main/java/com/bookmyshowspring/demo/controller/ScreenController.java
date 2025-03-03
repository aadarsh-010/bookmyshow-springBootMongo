package com.bookmyshowspring.demo.controller;


import com.bookmyshowspring.demo.dto.ScreenDTO;
import com.bookmyshowspring.demo.dto.ScreenSeatDTO;
import com.bookmyshowspring.demo.models.ScreenSeat;
import com.bookmyshowspring.demo.services.ScreenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    private final ScreenService screenService;
    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }


    @PostMapping
    public ResponseEntity<ScreenDTO> createScreen(@RequestBody ScreenDTO screenDTO) {
        ScreenDTO createdScreen = screenService.CreateScreen(screenDTO);
        return ResponseEntity.ok(createdScreen);
    }

    @PostMapping("/seats")
    public ResponseEntity<ScreenSeatDTO> createScreenSeats(@RequestBody ScreenSeatDTO screenSeatDTO) {
        ScreenSeatDTO createdSeats = screenService.CreateScreenSeats(screenSeatDTO);
        return ResponseEntity.ok(createdSeats);
    }

    @GetMapping("/{screenId}/seats")
    public ResponseEntity<ScreenSeat> getScreenSeat(@PathVariable String screenId) {
        try {
            return ResponseEntity.ok(screenService.getScreenSeat(screenId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{screenId}")
    public ResponseEntity<String> deleteScreen(@PathVariable String screenId) {
        screenService.DeleteScreen(screenId);
        return ResponseEntity.ok("Screen deleted successfully.");
    }
}
