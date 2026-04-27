package org.learnjava.flightsystem.flightbooking.exception;

import java.time.Instant;

public record ApiError(
        int status,
        String error,
        String message,
        Instant timeStamp
) {
}
