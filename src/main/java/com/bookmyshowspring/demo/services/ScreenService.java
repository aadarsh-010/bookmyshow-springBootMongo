package com.bookmyshowspring.demo.services;


import com.bookmyshowspring.demo.dto.ScreenDTO;
import com.bookmyshowspring.demo.dto.ScreenSeatDTO;
import com.bookmyshowspring.demo.enums.SeatBookingStatus;
import com.bookmyshowspring.demo.enums.SeatType;
import com.bookmyshowspring.demo.event.*;
import com.bookmyshowspring.demo.models.*;
import com.bookmyshowspring.demo.repository.ScreenRepository;
import com.bookmyshowspring.demo.repository.ScreenSeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class ScreenService {


    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    ScreenRepository screenrepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ScreenSeatRepository screenSeatRepo;
    @Autowired
    private ShowService showService;



    @Autowired
    public ScreenService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    public ScreenSeatDTO CreateScreenSeats(ScreenSeatDTO screenseatdto) {

        ScreenSeat screenseat = modelMapper.map(screenseatdto, ScreenSeat.class);


        screenSeatRepo.saveScreenSeat(screenseat);
        return modelMapper.map(screenseat, ScreenSeatDTO.class);


    }

    public ScreenDTO CreateScreen(ScreenDTO screendto) {
        Screen screen = modelMapper.map(screendto, Screen.class);
        screenrepo.saveScreen(screen);
        addScreenToTheatre(screen.getTheatreid(),screen.getId());
        return modelMapper.map(screen, ScreenDTO.class);
    }

    public ScreenSeat getScreenSeat(String screenid) throws Exception {
        Screen screen = screenrepo.findById(screenid)
                .orElseThrow(() -> new Exception("Screen not found"));

        return screenSeatRepo.findById(screen.getScreenSeatID())
                .orElseThrow(() -> new Exception("screenSeat not found"));
    }

    public void addScreenToTheatre(String Theatreid, String Screenid) {
        eventPublisher.publishEvent(new ScreenAddInTheaterEvent(Screenid, Theatreid));
    }

    public void removeScreenToTheatre(String Theatreid, String Screenid) {
        eventPublisher.publishEvent(new ScreenRemoveFromTheaterEvent(Screenid, Theatreid));
    }

    @Transactional
    @EventListener
    public void handleShowAddingToScreen(ShowAddInScreenEvent event) {
        Screen screen = screenrepo.findById(event.getScreenId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        screen.setShowRef(event.getShowId());
    }

    @Transactional
    @EventListener
    public void handleShowRemovalToScreen(ShowRemovalToScreenEvent event) {
        Screen screen = screenrepo.findById(event.getScreenId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        screen.getShowRef().remove(event.getShowId());
        screenrepo.saveScreen(screen);
    }

    public void DeleteScreen(String sid) {
        Screen screen = screenrepo.findById(sid)
                .orElseThrow(() -> new RuntimeException("User not found"));
        for (int i = 0; i < screen.getShowRef().size(); i++) {
            showService.DeleteShow(screen.getShowRef().get(i));
        }
        removeScreenToTheatre(sid, screen.getTheatreid());
        screenSeatRepo.deleteById(screen.getScreenSeatID());
        screenrepo.deleteById(sid);
    }

    @Transactional
    @EventListener
    public void handleSeatCreationEvent(SeatCreationEvent event) throws Exception {
        Show show= event.getShow();
        ShowSeats showSeats = event.getShowSeats();
        Optional<ScreenSeat> screenSeatOptional = screenSeatRepo.findById(show.getShowSeatRef());
        if (screenSeatOptional.isEmpty()) {
            throw new Exception("INVALID shows eat ID");
        }
        ScreenSeat screenSeat = screenSeatOptional.get();
        for(Map.Entry<SeatType, Integer> mapElement : screenSeat.getSeatTypeAndCount().entrySet()){
            for (int i = 0; i < mapElement.getValue(); i++) {
                Seat obj = new Seat(SeatBookingStatus.Open,mapElement.getKey(),show.getId(),showSeats.getPricePerSeatType().get(mapElement.getKey()));
                String newGeneratedId = showService.addSeatToSeatRepo(obj).getId();
                showService.addshowSeatsRef(show.getId(),newGeneratedId);
            }
        }
    }
}
