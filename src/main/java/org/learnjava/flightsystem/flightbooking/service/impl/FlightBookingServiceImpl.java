package org.learnjava.flightsystem.flightbooking.service.impl;

import org.learnjava.flightsystem.flightbooking.entity.Booking;
import org.learnjava.flightsystem.flightbooking.repo.BookingRepository;
import org.learnjava.flightsystem.flightbooking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FlightBookingServiceImpl extends BookingService {

    private final BookingRepository bookingRepository;


    public FlightBookingServiceImpl(BookingRepository bookingRepository) {
        super();
        this.bookingRepository = bookingRepository;
    }
    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Integer bookingId){
        return bookingRepository.findById(bookingId);
    }
    @Override
    public Booking createBooking(Booking booking){
        if(booking == null){
            throw new IllegalArgumentException("Booking must not be null");
        }
        if (booking.getUserId()==null){
            throw new IllegalArgumentException("User id is required");

        }
        if (booking.getFlightId() == null){
            throw new IllegalArgumentException("Flight id is required");

        }
        if (booking.getStatus() == null || booking.getStatus().isBlank()){
            throw new IllegalArgumentException("Status is required");

        }
        return bookingRepository.save(booking);
    }
}

