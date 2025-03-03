package com.bookmyshowspring.demo.models;


import com.bookmyshowspring.demo.enums.SeatType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;


@Document(collection = "showseats")
public class ShowSeats {

    @Id
    private String showseatsid;
    private String screenseatref;
    HashMap<SeatType,Integer> PricePerSeatType;
    private ArrayList<String> showSeatsRef;

    public ShowSeats(String showseatsid, String screenseatref, HashMap<SeatType, Integer> pricePerSeatType) {
        this.showseatsid = showseatsid;
        this.screenseatref = screenseatref;
        PricePerSeatType = pricePerSeatType;
        this.showSeatsRef = new ArrayList<>();
    }

    public String getShowseatsid() {
        return showseatsid;
    }

    public void setShowseatsid(String showseatsid) {
        this.showseatsid = showseatsid;
    }

    public String getScreenseatref() {
        return screenseatref;
    }

    public void setScreenseatref(String screenseatref) {
        this.screenseatref = screenseatref;
    }

    public HashMap<SeatType, Integer> getPricePerSeatType() {
        return PricePerSeatType;
    }

    public void setPricePerSeatType(HashMap<SeatType, Integer> pricePerSeatType) {
        PricePerSeatType = pricePerSeatType;
    }

    public ArrayList<String> getShowSeatsRef() {
        return showSeatsRef;
    }

    public void setShowSeatsRef(String showSeatsRef) {
        this.showSeatsRef.add(showSeatsRef);
    }
}
