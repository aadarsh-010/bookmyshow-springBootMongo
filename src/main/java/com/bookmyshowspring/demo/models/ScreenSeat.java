package com.bookmyshowspring.demo.models;


import com.bookmyshowspring.demo.enums.SeatType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;


@Document(collection = "screenseat")
public class ScreenSeat {

    @Id
    String id;
    int totalSeats;
    HashMap<SeatType,Integer> seatTypeAndCount;

    public ScreenSeat(String id ,int totalSeats, HashMap<SeatType, Integer> seatTypeAndCount) {
        this.totalSeats = totalSeats;
        this.seatTypeAndCount = seatTypeAndCount;
        this.id=id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public HashMap<SeatType, Integer> getSeatTypeAndCount() {
        return seatTypeAndCount;
    }

    public void setSeatTypeAndCount(HashMap<SeatType, Integer> seatTypeAndCount) {
        this.seatTypeAndCount = seatTypeAndCount;
    }
}
