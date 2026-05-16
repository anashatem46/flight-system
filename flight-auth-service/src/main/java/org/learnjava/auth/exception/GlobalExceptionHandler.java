package org.learnjava.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleInvalidCredentials(
            InvalidCredentialsException exception
    ) {
        ApiError error = new ApiError(
                false,
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ApiError> handleDuplicateUser(
            DuplicateUserException exception
    ) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(createError(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ApiError> handleUserServiceException(
            UserServiceException exception
    ) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(createError(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(
            IllegalArgumentException exception
    ) {
        return badRequest(exception.getMessage());
    }

    private ResponseEntity<ApiError> badRequest(String message) {
        return ResponseEntity.badRequest()
                .body(createError(HttpStatus.BAD_REQUEST, message));
    }

    private ApiError createError(HttpStatus status, String message) {
        return new ApiError(
                false,
                LocalDateTime.now(),
                status.value(),
                status.name(),
                message
        );
    }
}
