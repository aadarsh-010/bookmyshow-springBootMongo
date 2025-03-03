package com.bookmyshowspring.demo.services;


import com.bookmyshowspring.demo.dto.TheatreDTO;
import com.bookmyshowspring.demo.event.*;
import com.bookmyshowspring.demo.models.Theatre;
import com.bookmyshowspring.demo.repository.TheatreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TheaterService {

    @Autowired
    TheatreRepository theaterRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ScreenService screenService;

    private final ApplicationEventPublisher eventPublisher;

    TheaterService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void CreateTheater(TheatreDTO theatreDTO) throws Exception {
        Theatre theatre = new Theatre(theatreDTO.getName(),theatreDTO.getOwnerId(),theatreDTO.getLocation());
        theatre = theaterRepo.saveTheatre(theatre);
        eventPublisher.publishEvent(new TheaterCreatedEvent(theatre.getOwnerId(), theatre.getId()));

    }

    public void deleteTheater(String theatreId) throws Exception {
        Optional<Theatre> theatreOptional = theaterRepo.findById(theatreId);
        if (theatreOptional.isEmpty()) {
            throw new Exception("INVALID THEATRE ID");
        }
        Theatre theatre = theatreOptional.get();
        for(int i = 0; i < theatre.getScreensRef().size(); i++) {
            screenService.DeleteScreen(String.valueOf(theatre.getScreensRef().get(i)));
        }
        eventPublisher.publishEvent(new TheaterDeletedEvent(theatre.getOwnerId(), theatre.getId()));
        theaterRepo.deleteById(theatre.getId());
    }


    @Transactional
    @EventListener
    public void addScreenInTheaterEvent(ScreenAddInTheaterEvent event) {

        Theatre theatre =  theaterRepo.findById(event.getTheatreId())
                .orElseThrow(() -> new RuntimeException("no such Theatre found"));
        theatre.setScreensRef(event.getScreenId());
        theaterRepo.saveTheatre(theatre);
    }

    @Transactional
    @EventListener
    public void removeScreenFromTheater(ScreenRemoveFromTheaterEvent event) {

        Theatre theatre =  theaterRepo.findById(event.getTheatreId())
                .orElseThrow(() -> new RuntimeException("no such Theatre found"));

        theatre.getScreensRef().remove(String.valueOf(event.getScreenId()));
        theaterRepo.saveTheatre(theatre);

    }

    @Transactional
    @EventListener
    public void addShowInTheaterEvent(ShowAddInTheaterEvent event) {
        Theatre theatre =  theaterRepo.findById(event.getTheatreId()).orElseThrow(() -> new RuntimeException("no such Theatre found"));
        theatre.setShowsInTheatreRef(event.getShowId());
        theaterRepo.saveTheatre(theatre);
    }

    @Transactional
    @EventListener
    public void removeShowFromTheaterEvent(ShowRemoveFromTheaterEvent event) {
        Theatre theatre =  theaterRepo.findById(event.getTheatreId()).orElseThrow(() -> new RuntimeException("no such Theatre found"));
        theatre.getShowsInTheatreRef().remove(String.valueOf(event.getShowId()));
        theaterRepo.saveTheatre(theatre);

    }

    @Transactional
    public Theatre getTheatreEvent(String theaterId) {
        return theaterRepo.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("no such Theatre found"));
    }


}
