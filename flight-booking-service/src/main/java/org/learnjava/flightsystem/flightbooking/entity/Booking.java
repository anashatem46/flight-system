package org.learnjava.flightsystem.flightbooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.learnjava.flightsystem.flightbooking.dto.enums.BookingStatus;

import java.time.Instant;


@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_id_gen")
    @SequenceGenerator(name = "bookings_id_gen", sequenceName = "bookings_booking_id_seq", allocationSize = 1)
    @Column(name = "booking_id", nullable = false)
    private Integer id;

    @Column(name = "booking_time")
    private Instant bookingTime = Instant.now() ;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private BookingStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();


    @CreationTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @Column(name = "flight_id", nullable = false)
    private Integer flightId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}




