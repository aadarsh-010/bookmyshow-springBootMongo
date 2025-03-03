package com.bookmyshowspring.demo.dto;

import java.util.ArrayList;

public class TheatreDTO {

    private String id;
    private String name;
    private String location;
    private String ownerId;
    private ArrayList<String> showsInTheatreRef;
    private ArrayList<String> screensRef;

    public TheatreDTO(String id, String name, String location, String ownerId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.ownerId = ownerId;
        this.showsInTheatreRef = new ArrayList<>();
        this.screensRef = new ArrayList<>();

    }


    public TheatreDTO() {

    }



    public ArrayList<String> getShowsInTheatreRef() {
        return showsInTheatreRef;
    }

    public ArrayList<String> getScreensRef() {
        return screensRef;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }





}
