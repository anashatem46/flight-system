package org.learnjava.auth.dto;

public record AuthResponse(
        String accessToken,
        String tokenType
) {
}