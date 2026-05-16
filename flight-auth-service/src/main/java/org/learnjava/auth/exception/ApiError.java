package org.learnjava.auth.exception;

import java.time.LocalDateTime;

public record ApiError(
        boolean success,
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}
