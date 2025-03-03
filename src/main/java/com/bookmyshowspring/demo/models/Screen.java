package com.bookmyshowspring.demo.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "screen")
public class Screen {

    @Id
    private String id;
    private String theatreid;
    private String screenSeatID;
    private ArrayList<String> showRef;


    public Screen(String id, String theatreid, String screenSeatID) {
        this.id = id;
        this.theatreid = theatreid;
        this.screenSeatID = screenSeatID;
        this.showRef = new ArrayList<>();
    }


    public List<Show> getScreens() {
        return null;
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

    public void setShowRef(String showRef) {
        this.showRef.add(showRef);
    }
}

