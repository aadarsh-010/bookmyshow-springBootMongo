package com.bookmyshowspring.demo.controller;


import com.bookmyshowspring.demo.dto.ScreenDTO;
import com.bookmyshowspring.demo.dto.ScreenSeatDTO;
import com.bookmyshowspring.demo.enums.SeatType;
import com.bookmyshowspring.demo.models.Screen;
import com.bookmyshowspring.demo.models.ScreenSeat;
import com.bookmyshowspring.demo.services.ScreenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    private final ScreenService screenService;
    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

//    json example sample {
//        "theatreid": "67cece6d2ce47834268a272e",
//            "totalSeats": 200,
//            "seatTypeAndCount": {
//        "PREMIUM": 30,
//                "REGULAR": 50,
//                "BALCONY": 20,
//                "GOLD": 25,
//                "SILVER": 35,
//                "PLATINUM": 20,
//                "DIAMOND": 20
//    }
//    }

    @PostMapping
    public ResponseEntity<ScreenDTO> createScreen(@RequestBody Map<String, Object> obj) {

                Object seatTypeAndCountObj = obj.get("seatTypeAndCount");
                HashMap<SeatType, Integer> seatTypeAndCountMap = new HashMap<>();
        System.out.println("as");
                if (seatTypeAndCountObj instanceof Map) {
                    Map<String, Object> seatTypeMap = (Map<String, Object>) seatTypeAndCountObj;
                    System.out.println("as");
                    for (Map.Entry<String, Object> entry : seatTypeMap.entrySet()) {
                        try {
                            SeatType seatType = SeatType.valueOf(entry.getKey().toUpperCase());
                            Integer count = Integer.parseInt(entry.getValue().toString());
                            seatTypeAndCountMap.put(seatType, count);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid seat type: " + entry.getKey());
                        }
                    }
                }

        ScreenSeatDTO ssdto = new ScreenSeatDTO((int) obj.get("totalSeats"),seatTypeAndCountMap);
        ScreenSeatDTO createdScreenSeatDTO = createScreenSeats(ssdto);
        ScreenDTO sc = new ScreenDTO((String)obj.get("theatreid"),createdScreenSeatDTO.getId());
        ScreenDTO createdScreen = screenService.CreateScreen(sc);
        return ResponseEntity.ok(createdScreen);
    }

    @PostMapping("/seats")
    public ScreenSeatDTO createScreenSeats(@RequestBody ScreenSeatDTO screenSeatDTO) {
        return screenService.CreateScreenSeats(screenSeatDTO);
    }

    @GetMapping("/seats/{screenId}")
    public ResponseEntity<ScreenSeat> getScreenSeat(@PathVariable String screenId) {
        try {
            System.out.println("asd");
            return ResponseEntity.ok(screenService.getScreenSeat(screenId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{screenId}")   //test
    public ResponseEntity<String> deleteScreen(@PathVariable String screenId) {
        screenService.DeleteScreen(screenId);
        return ResponseEntity.ok("Screen deleted successfully.");
    }
}
