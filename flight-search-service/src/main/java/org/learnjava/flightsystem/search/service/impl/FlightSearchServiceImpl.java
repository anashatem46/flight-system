package org.learnjava.flightsystem.search.service.impl;


import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.search.dto.FlightDto;
import org.learnjava.flightsystem.search.dto.FlightResponse;
import org.learnjava.flightsystem.search.entity.Flight;
import org.learnjava.flightsystem.search.enums.FlightStatus;
import org.learnjava.flightsystem.search.mapper.FlightSearchMapper;
import org.learnjava.flightsystem.search.repo.FlightRepository;
import org.learnjava.flightsystem.search.service.FlightSearchService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightRepository flightRepository;
    private final FlightSearchMapper flightSearchMapper;

    @Override
    public List<FlightDto> searchFlights(String origin, String destination, LocalDate departureDate) {

        Instant startOfDay = departureDate
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        Instant endOfDay = departureDate
                .plusDays(1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        List<Flight>  flights  = flightRepository.findByOriginAndDestinationAndDepartureTimeBetweenAndStatusAndAvailableSeatsGreaterThan(
                origin.toUpperCase(),
                destination.toUpperCase(),
                startOfDay,
                endOfDay,
                FlightStatus.SCHEDULED,
                1
        );
        return flights.stream().map(flightSearchMapper::convertToFlightDto).toList();
    }
}

