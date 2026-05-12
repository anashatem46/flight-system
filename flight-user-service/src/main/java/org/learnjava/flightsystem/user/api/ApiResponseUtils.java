package org.learnjava.flightsystem.user.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ApiResponseUtils {

    private ApiResponseUtils() {
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(new ApiResponse<>(true, message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, message, data));
    }

    public static ResponseEntity<ApiResponse<Void>> successWithoutData(String message) {
        return ResponseEntity.ok(new ApiResponse<>(true, message, null));
    }
}