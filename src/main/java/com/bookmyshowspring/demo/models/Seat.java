package com.bookmyshowspring.demo.models;


import com.bookmyshowspring.demo.enums.SeatBookingStatus;
import com.bookmyshowspring.demo.enums.SeatType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "seat")
public class Seat {


    @Id
    private String id;
    private SeatBookingStatus seatBookingStatus;
    private SeatType seatType;
    private String showid;
    private Integer price;
    
    public Seat(){}
    public Seat(String id, SeatBookingStatus seatBookingStatus, SeatType seatType, String showid, Integer price) {
        this.id = id;
        this.seatBookingStatus = seatBookingStatus;
        this.seatType = seatType;
        this.showid = showid;
        this.price = price;
    }

    public Seat( SeatBookingStatus seatBookingStatus, SeatType seatType, String showid, Integer price) {
        this.id = id;
        this.seatBookingStatus = seatBookingStatus;
        this.seatType = seatType;
        this.showid = showid;
        this.price = price;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public SeatBookingStatus getSeatBookingStatus() {
        return seatBookingStatus;
    }

    public void setSeatBookingStatus(SeatBookingStatus seatBookingStatus) {
        this.seatBookingStatus = seatBookingStatus;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public String getShowid() {
        return showid;
    }

    public void setShowid(String showid) {
        this.showid = showid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
