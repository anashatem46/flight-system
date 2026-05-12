package org.learnjava.flightsystem.user.exceptions;

import java.time.LocalDateTime;

public record ApiError(
        boolean success,
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}