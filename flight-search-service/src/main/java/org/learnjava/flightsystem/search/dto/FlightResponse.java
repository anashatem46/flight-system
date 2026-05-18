package org.learnjava.flightsystem.search.dto;

import org.learnjava.flightsystem.search.enums.FlightStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record FlightResponse (
        Integer flightId,
        String flightNumber,
        String airline,
        String origin,
        String destination,
        Instant departureTime,
        Instant arrivalTime,
        BigDecimal price,
        Integer availableSeats,
        FlightStatus status
) {
}

