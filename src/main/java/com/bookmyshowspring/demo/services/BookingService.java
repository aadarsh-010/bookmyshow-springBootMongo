package com.bookmyshowspring.demo.services;

import com.bookmyshowspring.demo.dto.BookingDTO;
import com.bookmyshowspring.demo.enums.BookingStatus;
import com.bookmyshowspring.demo.enums.SeatBookingStatus;
import com.bookmyshowspring.demo.event.BookingCreatedEvent;
import com.bookmyshowspring.demo.models.Booking;
import com.bookmyshowspring.demo.models.Seat;
import com.bookmyshowspring.demo.repository.BookingRepository;
import com.bookmyshowspring.demo.repository.SeatRepository;
import com.bookmyshowspring.demo.services.paymentservice.CreditCard;
import com.bookmyshowspring.demo.services.paymentservice.DebitCard;
import com.bookmyshowspring.demo.services.paymentservice.PaymentService;
import com.bookmyshowspring.demo.services.paymentservice.UPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class BookingService {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private SeatRepository seatRepo;

    private static int bookingID = 1000;

    public BookingService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public synchronized void bookShow(BookingDTO bookingdto) throws Exception {

        List<Seat> seats = (List<Seat>) seatRepo.findAllByIds(bookingdto.getBookedSeatsRef());
        boolean allAvailable = seats.stream().allMatch(seat -> seat.getSeatBookingStatus() == SeatBookingStatus.Open);
        if (!allAvailable) {
            System.out.println("Not enough tickets available to book.");
            return;
        }
        seats.forEach(seat -> seat.setSeatBookingStatus(SeatBookingStatus.Reserved));
        seatRepo.saveAllSeats(seats);
        double totalCost = seats.stream().mapToDouble(Seat::getPrice).sum();
        processPayment(totalCost);
        seats.forEach(seat -> seat.setSeatBookingStatus(SeatBookingStatus.Booked));
        seatRepo.saveAllSeats(seats);
        Booking booking = new Booking(String.valueOf(bookingID++), bookingdto.getCustomerId(), bookingdto.getShowId(), bookingdto.getBookedSeatsRef(), bookingdto.getTotalPrice(), BookingStatus.Confirmed);
        bookingRepo.saveBooking(booking);
        eventPublisher.publishEvent(new BookingCreatedEvent(bookingdto.getCustomerId(),booking.getId()));
        System.out.println("Booked " + bookingdto.getBookedSeatsRef().size() + " seats for a total price of " + totalCost);
    }

    private void processPayment(double totalPrice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SELECT PAYMENT METHOD: CARD / DEBIT / UPI");
        String input = sc.next().toUpperCase();
        PaymentService ps = null;
        switch (input) {
            case "CARD":
                System.out.println("Enter card number:");
                ps = new PaymentService(new CreditCard(sc.next()));
                break;
            case "DEBIT":
                System.out.println("Enter debit card number:");
                ps = new PaymentService(new DebitCard(sc.next()));
                break;
            case "UPI":
                System.out.println("Enter UPI ID:");
                ps = new PaymentService(new UPI(sc.next()));
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }
        ps.pay((int) totalPrice);
    }

    public void cancelBooking(String bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
        if (bookingOpt.isEmpty()) {
            System.out.println("Booking not found.");
            return;
        }
        Booking booking = bookingOpt.get();
        List<Seat> seats = (List<Seat>) seatRepo.findAllByIds(booking.getBookedSeatsRef());
        seats.forEach(seat -> seat.setSeatBookingStatus(SeatBookingStatus.Open));
        seatRepo.saveAllSeats(seats);
        booking.setBookingStatus(BookingStatus.Cancelled);
        bookingRepo.saveBooking(booking);
        System.out.println("Booking " + bookingId + " has been cancelled.");
    }
}
