package org.learnjava.flightsystem.search.controller;


import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.search.dto.FlightDto;
import org.learnjava.flightsystem.search.service.FlightSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
@RequiredArgsConstructor
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    @GetMapping("/search")
    public List<FlightDto> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate departureDate
    ) {
        return flightSearchService.searchFlights(
                departureAirport,
                arrivalAirport,
                departureDate
        );
    }


}
