package com.bookmyshowspring.demo.event;

public class ScreenRemoveFromTheaterEvent {
    String screenId;
    String theatreId;

    public ScreenRemoveFromTheaterEvent(String screenId, String theatreId) {
        this.screenId = screenId;
        this.theatreId = theatreId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }
}
