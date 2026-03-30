package org.learnjava.flightsystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.learnjava.flightsystem.flightbooking.entity.Booking;
import org.learnjava.flightsystem.flightbooking.repo.BookingRepository;
import org.learnjava.flightsystem.flightbooking.service.impl.FlightBookingServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        booking.setStatus("CONFIRMED");
        booking.setFlightId(101);
        booking.setUserId(5);
    }


    @Test
    void getAllBookings_shouldReturnAllBookings(){
        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        List<Booking> result = flightBookingService.getAllBookings();
        assertEquals(1, result.size());
        assertEquals("CONFIRMED", result.get(0).getStatus());
        verify(bookingRepository).findAll();

    }






}


