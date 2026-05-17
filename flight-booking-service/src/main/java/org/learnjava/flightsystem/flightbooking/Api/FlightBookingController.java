package org.learnjava.flightsystem.flightbooking.Api;


import jakarta.validation.Valid;
import org.learnjava.flightsystem.flightbooking.dto.BookingDto;
import org.learnjava.flightsystem.flightbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class FlightBookingController {
    private  final BookingService flightBookingService;

    public FlightBookingController(BookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    @GetMapping
    public List<BookingDto> getAllBookings(){
        return flightBookingService.getAllBookings();

    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@Valid @PathVariable Integer id){
        Optional<BookingDto> booking = flightBookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingDto bookingDto){
        BookingDto saveBooking = flightBookingService.createBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBooking);
    }



}
