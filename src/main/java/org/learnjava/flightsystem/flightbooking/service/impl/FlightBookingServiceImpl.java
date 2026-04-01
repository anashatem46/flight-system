package org.learnjava.flightsystem.flightbooking.service.impl;

import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.flightbooking.dto.BookingDto;
import org.learnjava.flightsystem.flightbooking.dto.enums.BookingStatus;
import org.learnjava.flightsystem.flightbooking.entity.Booking;
import org.learnjava.flightsystem.flightbooking.repo.BookingRepository;
import org.learnjava.flightsystem.flightbooking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightBookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;


    private BookingDto toDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getUserId(),
                booking.getFlightId(),
                booking.getBookingTime(),
                booking.getStatus() == null ? null : BookingStatus.valueOf(String.valueOf(booking.getStatus()))
        );
    }

    private Booking toEntity(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setId(bookingDto.bookingId());
        booking.setUserId(bookingDto.userId());
        booking.setFlightId(bookingDto.flightId());
        booking.setBookingTime(bookingDto.bookingTime());
        booking.setStatus(bookingDto.status() == null ? null : BookingStatus.valueOf(bookingDto.status().name()));
        return booking;
    }
    @Override
    public List<BookingDto> getAllBookings(){
        return bookingRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Optional<BookingDto> getBookingById(Integer bookingId){
        return bookingRepository.findById(bookingId).map(this::toDto);
    }
    @Override
    public BookingDto createBooking(BookingDto bookingDto){
        if(bookingDto == null){
            throw new IllegalArgumentException("Booking must not be null");
        }
        if (bookingDto.userId()==null){
            throw new IllegalArgumentException("User id is required");

        }
        if (bookingDto.flightId() == null){
            throw new IllegalArgumentException("Flight id is required");

        }
        if (bookingDto.status() == null){
            throw new IllegalArgumentException("Status is required");

        }
        Booking booking =toEntity(bookingDto);
        Booking savedBooking = bookingRepository.save(booking);
        return toDto(savedBooking);
    }
}

