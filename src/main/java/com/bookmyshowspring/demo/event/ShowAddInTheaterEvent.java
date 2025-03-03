package com.bookmyshowspring.demo.event;

public class ShowAddInTheaterEvent {

    String showId;
    String theatreId;

    public ShowAddInTheaterEvent(String showId, String theatreId) {
        this.showId = showId;
        this.theatreId = theatreId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }
}
