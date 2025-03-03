package com.bookmyshowspring.demo.event;


import com.bookmyshowspring.demo.models.Show;
import com.bookmyshowspring.demo.models.ShowSeats;

public class SeatCreationEvent {

    Show show;
    ShowSeats ShowSeats;

    public SeatCreationEvent(Show show, com.bookmyshowspring.demo.models.ShowSeats showSeats) {
        this.show = show;
        ShowSeats = showSeats;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public ShowSeats getShowSeats() {
        return ShowSeats;
    }

    public void setShowSeats(ShowSeats showSeats) {
        ShowSeats = showSeats;
    }
}
