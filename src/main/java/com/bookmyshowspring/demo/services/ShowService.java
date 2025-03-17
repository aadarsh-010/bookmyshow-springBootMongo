package com.bookmyshowspring.demo.services;


import com.bookmyshowspring.demo.dto.ShowDTO;
import com.bookmyshowspring.demo.dto.ShowSeatsDTO;
import com.bookmyshowspring.demo.enums.SeatBookingStatus;
import com.bookmyshowspring.demo.enums.SeatType;
import com.bookmyshowspring.demo.event.*;
import com.bookmyshowspring.demo.models.Seat;
import com.bookmyshowspring.demo.models.Show;
import com.bookmyshowspring.demo.models.ShowSeats;
import com.bookmyshowspring.demo.repository.mongo.SeatMongoRepository;
import com.bookmyshowspring.demo.repository.mongo.ShowMongoRepository;
import com.bookmyshowspring.demo.repository.mongo.ShowSeatMongoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShowService {


    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    ShowMongoRepository showrepo;
    @Autowired
    ShowSeatMongoRepository showseatrepo;
    @Autowired
    MovieService movieService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private SeatMongoRepository seatrepo;
    @Autowired
    public ShowService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    public void addshowSeatsRef(ShowSeats showSeats, String newGeneratedId) throws Exception {
        showSeats.setShowSeatsRef(newGeneratedId);
        showseatrepo.saveShowSeat(showSeats);
    }

    public Seat addSeatToSeatRepo(Seat obj) {
        return seatrepo.saveSeat(obj);

    }

    public ShowDTO CreateShow(ShowDTO showdto , ShowSeatsDTO showSeatdto) {

        ShowSeats showseats = new ShowSeats(showSeatdto.getScreenseatref(),showSeatdto.getPricePerSeatType());
        ShowSeats showSeats = showseatrepo.saveShowSeat(showseats);
        Show show = new Show(showdto.getMovieId(),showdto.getTheatreId(),showdto.getScreenId(),showdto.getStartTime(),showdto.getEndTime(),showSeats.getShowseatsid());
        Show showNew = showrepo.saveShow(show);
        System.out.println("zcs");
        eventPublisher.publishEvent(new ShowAddInScreenEvent(show.getId(),show.getScreenId()));
        eventPublisher.publishEvent(new ShowAddInTheaterEvent(show.getId(),show.getTheatreId()));
        System.out.println("yha tk sort");
        generateSeats(show,showseats);
        addShowRefToMovie(show.getId(), show.getMovieId());
        return modelMapper.map( showNew,ShowDTO.class);
    }

    public void generateSeats(Show show, ShowSeats showSeats){
        eventPublisher.publishEvent(new SeatCreationEvent(show , showSeats));
    };

    public void DeleteShow(String showid) {
        Show s1 = showrepo.findById(showid).orElseThrow(() -> new RuntimeException("show id wrong not found !!"));
        ShowSeats showSeats = showseatrepo.findById(s1.getShowSeatRef()).orElseThrow(() -> new RuntimeException("show id wrong not found !!"));
        for(int i=0;i<showSeats.getShowSeatsRef().size();i++){
            seatrepo.deleteById(showSeats.getShowSeatsRef().get(i));
        }
        removeshowtoScreen(showid,s1.getScreenId());
        removeshowtoTheatre(showid,s1.getTheatreId());
        showseatrepo.deleteById(s1.getShowSeatRef());
        showrepo.deleteById(showid);
    }


    public void addshowtoTheatre(String showid , String thid){
        eventPublisher.publishEvent(new ShowAddInTheaterEvent(showid,thid));;
    }


    public void addshowtoScreen(String showid,String screenId) {
        eventPublisher.publishEvent(new ShowAddInScreenEvent(showid ,screenId));;
    }

    public void removeshowtoTheatre(String thid,String showid) {
        eventPublisher.publishEvent(new ShowRemoveFromTheaterEvent(showid,thid));

    }

    public void removeshowtoScreen(String showid,String screenid) {
        eventPublisher.publishEvent(new ShowRemovalToScreenEvent(showid,screenid));
    }

    public void addShowRefToMovie(String showid, String mid){
        movieService.addShowRefToMovie(showid,mid);
    }

    public int getAvailableSeats(String showId, SeatType seatType) {
        Show show = showrepo.findById(showId).orElseThrow(() -> new RuntimeException("Show ID not found!"));
        List<String> seatRefs = showseatrepo.findById(show.getShowSeatRef()).orElseThrow(() -> new RuntimeException("Show ID not found!")).getShowSeatsRef(); // Assuming this stores seat IDs
        List<Seat> seats = (List<Seat>) seatrepo.findAllByIds(seatRefs);
        return (int) seats.stream().filter(seat -> seat.getSeatType() == seatType && seat.getSeatBookingStatus() == SeatBookingStatus.Open).count();
    }

    public Show getAvaiableShow(String key1) {
        return showrepo.findById(key1).orElseThrow(() -> new RuntimeException("Show ID not found!"));
    }
}
