package org.learnjava.flightsystem.flightbooking.service;

import org.learnjava.flightsystem.flightbooking.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(Integer bookingId);
    Booking createBooking(Booking booking);

}
