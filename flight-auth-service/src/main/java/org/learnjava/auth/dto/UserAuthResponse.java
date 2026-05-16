package org.learnjava.auth.dto;

public record UserAuthResponse(
        Integer id,
        String username,
        String email,
        String passwordHash,
        String role
) {
}