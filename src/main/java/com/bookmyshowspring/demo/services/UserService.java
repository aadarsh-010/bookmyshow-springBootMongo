package com.bookmyshowspring.demo.services;


import com.bookmyshowspring.demo.dto.user.UserDTO;
import com.bookmyshowspring.demo.enums.UserType;
import com.bookmyshowspring.demo.event.BookingCreatedEvent;
import com.bookmyshowspring.demo.event.TheaterCreatedEvent;
import com.bookmyshowspring.demo.event.TheaterDeletedEvent;
import com.bookmyshowspring.demo.models.user.Creator;
import com.bookmyshowspring.demo.models.user.Customer;
import com.bookmyshowspring.demo.models.user.User;
import com.bookmyshowspring.demo.repository.CreatorRepository;
import com.bookmyshowspring.demo.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    CreatorRepository creatorRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TheaterService theaterService;

    @Autowired
    BookingService bookingService;

    public UserDTO createUser(UserDTO userdto) {


        User returnable;
        if (userdto.getUsertype() == UserType.CREATOR) {
            Creator user = modelMapper.map(userdto, Creator.class);
            returnable = creatorRepo.saveCreator(user);
        } else if (userdto.getUsertype() == UserType.CUSTOMER) {
            Customer user = modelMapper.map(userdto, Customer.class);
            returnable = customerRepo.saveCustomer(user);
        } else {
            throw new IllegalArgumentException("Invalid UserType provided.");
        }


        return modelMapper.map(returnable, UserDTO.class);

    }


    public void deleteUser(String id) throws Exception {
        System.out.println("hello");
        Optional<Customer> customerOptional;
        System.out.println("hello");
        customerOptional = customerRepo.findById(id);
        System.out.println("hello");
        if (customerOptional.isEmpty()){
            Optional<Creator> creatorOptional;
            creatorOptional =  creatorRepo.findById(id);
            if (creatorOptional.isEmpty()) throw new Exception("INVALID USER ID");

            Creator creator = creatorOptional.get();
            for (String theatreId : creator.getTheatresOwned()) {
                theaterService.deleteTheater(theatreId);
            }
            creatorRepo.deleteById(creator.getUserid());
        }
        Customer customer = customerOptional.get();
        System.out.println("hello");
        for (String bookingID : customer.getUserBookings()) {
            bookingService.cancelBooking(bookingID);
        }
        customerRepo.deleteById(customer.getUserid());
    }


    public List<String> getOwnedTheatres(String userId) throws Exception {
        Creator creator = creatorRepo.findById(userId).orElseThrow(() -> new Exception("Creator not found"));
        return creator.getTheatresOwned();
    }

    @Transactional
    @EventListener
    public void handleTheaterCreated(TheaterCreatedEvent event) {
        System.out.println(event.getUserId());
        Creator creator = creatorRepo.findById(event.getUserId()).orElseThrow(() -> new RuntimeException("Creator not found"));
        creator.getTheatresOwned().add(event.getTheaterId());
        creatorRepo.saveCreator(creator);
    }

    @Transactional
    @EventListener
    public void handleTheaterDeleted(TheaterDeletedEvent event) {
        Creator creator = creatorRepo.findById(event.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        creator.getTheatresOwned().remove(String.valueOf(event.getTheaterId()));
        creatorRepo.saveCreator(creator);
    }


    public ArrayList<String> getBookings(String userid){
        Customer customer = customerRepo.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        return customer.getUserBookings();
    }

    public void removeUserBookings(String userid, String bid){
        Customer customer = customerRepo.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        if (customer.getUserBookings().stream().anyMatch(b -> b.equals(bid))) {
            customer.getUserBookings().remove(bid);
            bookingService.cancelBooking(bid);
            customerRepo.saveCustomer(customer);
        } else {
            throw new RuntimeException("Booking id provided is not present in customer list");
        }
    }

    public void handleBookingCreated(BookingCreatedEvent event) {
        Customer customer = customerRepo.findById(event.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        customer.setUserBookings(event.getBookingId());
    }


}



