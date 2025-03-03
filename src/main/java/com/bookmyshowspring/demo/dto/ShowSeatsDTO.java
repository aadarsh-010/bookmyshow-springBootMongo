package com.bookmyshowspring.demo.dto;

import com.bookmyshowspring.demo.enums.SeatType;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowSeatsDTO {

    private String showseatsid;
    private String screenseatref;
    HashMap<SeatType,Integer> PricePerSeatType;
    private ArrayList<String> showSeatsRef;

    public ShowSeatsDTO(String showseatsid, String screenseatref, HashMap<SeatType, Integer> pricePerSeatType) {
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

    public void setShowSeatsRef(ArrayList<String> showSeatsRef) {
        this.showSeatsRef = showSeatsRef;
    }
}
