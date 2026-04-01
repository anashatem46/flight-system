package org.learnjava.flightsystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.learnjava.flightsystem.flightbooking.dto.BookingDto;
import org.learnjava.flightsystem.flightbooking.dto.enums.BookingStatus;
import org.learnjava.flightsystem.flightbooking.entity.Booking;
import org.learnjava.flightsystem.flightbooking.repo.BookingRepository;
import org.learnjava.flightsystem.flightbooking.service.impl.FlightBookingServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightBookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private FlightBookingServiceImpl flightBookingService;


    private Booking booking;


    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setId(1);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setFlightId(101);
        booking.setUserId(5);
    }


    @Test
    void getAllBookings_shouldReturnAllBookings(){
        when(bookingRepository.findAll()).thenReturn(List.of(booking));

        List<BookingDto> result = flightBookingService.getAllBookings();


        assertEquals(1, result.size());
        assertEquals(BookingStatus.CONFIRMED, result.getFirst().status());
        verify(bookingRepository).findAll();

    }
    @Test
    void getBookingById_shouldReturnBooking_whenBookingExists() {
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        Optional<BookingDto> result = flightBookingService.getBookingById(1);

        BookingDto found = result.orElseThrow();
        assertEquals(1, found.bookingId());
        verify(bookingRepository).findById(1);

    }

    @Test
    void getBookingById_shouldReturnEmptyOptional_whenBookingNotExists() {
        when(bookingRepository.findById(99)).thenReturn(Optional.empty());
        Optional<BookingDto> result = flightBookingService.getBookingById(99);

        assertTrue(result.isEmpty());

        verify(bookingRepository).findById(99);

    }






}


