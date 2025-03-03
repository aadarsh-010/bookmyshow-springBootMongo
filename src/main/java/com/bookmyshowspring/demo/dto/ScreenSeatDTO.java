package com.bookmyshowspring.demo.dto;



import com.bookmyshowspring.demo.enums.SeatType;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;

public class ScreenSeatDTO {
    String id;
    int totalSeats;
    HashMap<SeatType,Integer> seatTypeAndCount;

    public ScreenSeatDTO(String id ,int totalSeats, HashMap<SeatType, Integer> seatTypeAndCount){
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
