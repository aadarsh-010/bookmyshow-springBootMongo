package com.bookmyshowspring.demo.event;

public class ShowAddInScreenEvent {

    String showId;
    String screenId;

    public ShowAddInScreenEvent(String showId, String screenId) {
        this.showId = showId;
        this.screenId = screenId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }
}
