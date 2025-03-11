package com.bookmyshowspring.demo.dto;



import com.bookmyshowspring.demo.models.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDTO {


    private String id;
    private String theatreid;
    private String screenSeatID;
    private ArrayList<String> showRef;


    public ScreenDTO(String theatreid,String screenSeatID) {
        this.theatreid=theatreid;
        this.screenSeatID=screenSeatID;
        showRef = new ArrayList<>();
    }
    public ScreenDTO() {
        showRef = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatreid() {
        return theatreid;
    }

    public void setTheatreid(String theatreid) {
        this.theatreid = theatreid;
    }

    public String getScreenSeatID() {
        return screenSeatID;
    }

    public void setScreenSeatID(String screenSeatID) {
        this.screenSeatID = screenSeatID;
    }

    public ArrayList<String> getShowRef() {
        return showRef;
    }

    public void setShowRef(ArrayList<String> showRef) {
        this.showRef = showRef;
    }
}

