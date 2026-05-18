package org.learnjava.flightsystem.search.service;


import org.learnjava.flightsystem.search.dto.FlightDto;


import java.time.LocalDate;
import java.util.List;

public interface FlightSearchService {
    List<FlightDto> searchFlights(String origin,
                                  String destination,
                                  LocalDate departureDate);

}
