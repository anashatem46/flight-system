package org.learnjava.flightsystem.search.repo;

import org.learnjava.flightsystem.search.entity.Flight;
import org.learnjava.flightsystem.search.enums.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight,Integer> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByOriginAndDestinationAndDepartureTimeBetweenAndStatusAndAvailableSeatsGreaterThan(
            String origin,
            String destination,
            Instant startOfDay,
            Instant endOfDay,
            FlightStatus status,
            Integer minSeats
    );
}
