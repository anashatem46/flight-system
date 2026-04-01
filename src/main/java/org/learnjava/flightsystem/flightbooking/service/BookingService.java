package org.learnjava.flightsystem.flightbooking.service;

import org.learnjava.flightsystem.flightbooking.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<BookingDto> getAllBookings();

    Optional<BookingDto> getBookingById(Integer bookingId);
    BookingDto createBooking(BookingDto bookingDto);

}
