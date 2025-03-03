package com.bookmyshowspring.demo.dto;

public class ShowDTO {
    private String id;
    private String movieId;
    private String theatreId;
    private String screenId;
    private String startTime;
    private String endTime;
    private String showSeatRef;


    public ShowDTO(String id, String movieId, String theatreId, String screenId, String startTime, String endTime, String showSeatRef) {
        this.id = id;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showSeatRef = showSeatRef;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getShowSeatRef() {
        return showSeatRef;
    }

    public void setShowSeatRef(String showSeatRef) {
        this.showSeatRef = showSeatRef;
    }
}
