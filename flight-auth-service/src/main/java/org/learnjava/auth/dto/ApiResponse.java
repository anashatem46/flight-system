package org.learnjava.auth.dto;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}