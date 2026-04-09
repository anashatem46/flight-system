package org.learnjava.flightsystem.flightbooking.dto;


import jakarta.validation.constraints.NotNull;
import org.learnjava.flightsystem.flightbooking.dto.enums.BookingStatus;


import java.time.Instant;

public record BookingDto(
        Integer bookingId,

        @NotNull(message = "userId can't be null")
        Integer userId,

        @NotNull(message = "flightId can't be null")
        Integer flightId,

        Instant bookingTime,

        @NotNull(message = "Status cant be ull")
        BookingStatus status
) {}