package com.bookmyshowspring.demo.controller;

import com.bookmyshowspring.demo.dto.ShowDTO;
import com.bookmyshowspring.demo.dto.ShowSeatsDTO;
import com.bookmyshowspring.demo.enums.SeatType;
import com.bookmyshowspring.demo.models.Show;
import com.bookmyshowspring.demo.services.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }


//    JSON EXAMPLE {
//        "movieId": "67cf95011ad5032ad7c6f0cf",
//            "theatreId": "67cece6d2ce47834268a272e",
//            "screenId": "67cf47ede31947783240c830",
//            "startTime": "2025-03-12T10:00:00",
//            "endTime": "2025-03-12T12:45:00",
//            "screenseatref": "67cf47ede31947783240c82f",
//            "PricePerSeatType": {
//        "PREMIUM": 320,
//                "REGULAR": 190,
//                "BALCONY": 270,
//                "GOLD": 360,
//                "SILVER": 155,
//                "PLATINUM": 410,
//                "DIAMOND": 530
//    }
//    }

    @PostMapping("/create")
    public ResponseEntity<ShowDTO> createShow( @RequestBody Map<String, Object> obj) {

        Object pricePerSeatTypeobj = obj.get("PricePerSeatType");
        HashMap<SeatType,Integer> pricePerSeatType = new HashMap<>();
        System.out.println("as");
        if (pricePerSeatTypeobj instanceof Map) {
            Map<String, Object> mapObj = (Map<String, Object>) pricePerSeatTypeobj;
            System.out.println("as");
            for (Map.Entry<String, Object> entry : mapObj.entrySet()) {
                try {
                    SeatType seatType = SeatType.valueOf(entry.getKey().toUpperCase());
                    Integer count = Integer.parseInt(entry.getValue().toString());
                    pricePerSeatType.put(seatType, count);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid seat type: " + entry.getKey());
                }
            }
        }

        ShowSeatsDTO showSeatsDTO = new ShowSeatsDTO((String) obj.get("screenseatref"),pricePerSeatType);
        ShowDTO showDTO = new ShowDTO((String) obj.get("movieId"),(String) obj.get("theatreId"),(String) obj.get("screenId"),(String) obj.get("startTime"),(String)obj.get("endTime"));
        ShowDTO createdShow = showService.CreateShow(showDTO, showSeatsDTO);
        return ResponseEntity.ok(createdShow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable String id) {
        return ResponseEntity.ok(showService.getAvaiableShow(id));
    }

    @DeleteMapping("/{id}") //test
    public ResponseEntity<String> deleteShow(@PathVariable String id) {
        showService.DeleteShow(id);
        return ResponseEntity.ok("Show deleted successfully");
    }

    @GetMapping("/{id}/available-seats")  //test
    public ResponseEntity<Integer> getAvailableSeats(@PathVariable String id, @RequestParam SeatType seatType) {
        int availableSeats = showService.getAvailableSeats(id, seatType);
        return ResponseEntity.ok(availableSeats);
    }
}
