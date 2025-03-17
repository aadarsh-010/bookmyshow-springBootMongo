package com.bookmyshowspring.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "theatre")
public class Theatre {

    @Id
    private String id;
    private String name;
    private String location;
    private String ownerId;
    private  ArrayList<String> showsInTheatreRef;
    private  ArrayList<String> screensRef;

    public Theatre(String id, String name, String location, String ownerId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.ownerId = ownerId;
        this.showsInTheatreRef=new ArrayList<>();
        this.screensRef=new ArrayList<>();
    }


    public Theatre( String name, String ownerId,String location) {
        this.name = name;
        this.location = location;
        this.ownerId = ownerId;
        this.showsInTheatreRef=new ArrayList<>();
        this.screensRef=new ArrayList<>();
    }

    public Theatre() {
    }


    public ArrayList<String> getShowsInTheatreRef() {
        return showsInTheatreRef;
    }

    public ArrayList<String> getScreensRef() {
        return screensRef;
    }

    public void setScreensRef(String screenId){
        this.screensRef.add(screenId);
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

    public void setShowsInTheatreRef(String showId){
        this.showsInTheatreRef.add(showId);
    }



}
