package org.learnjava.flightsystem.flightbooking.Api;


import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.flightbooking.entity.Booking;
import org.learnjava.flightsystem.flightbooking.service.BookingService;
import org.learnjava.flightsystem.flightbooking.service.impl.FlightBookingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class FlightBookingController {
    private  final BookingService flightBookingService;

    public FlightBookingController(BookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    @GetMapping("/")
    public List<Booking> getAllBookings(){
        return flightBookingService.getAllBookings();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id){
        Optional<Booking> booking = flightBookingService.getBookingById(id);
        if(booking.isPresent()){
            return ResponseEntity.ok(booking.get());


        } return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        Booking saveBooking = flightBookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBooking);
    }


}
