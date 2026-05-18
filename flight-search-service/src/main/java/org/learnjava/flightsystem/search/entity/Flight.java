package org.learnjava.flightsystem.search.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.learnjava.flightsystem.search.enums.FlightStatus;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
@Entity
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "flight_id")
    private Integer id;


    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private Instant departureTime;

    @Column(name = "arrival_time", nullable = false)
    private Instant arrivalTime;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}

