package org.learnjava.flightsystem.user.dto;

import org.learnjava.flightsystem.user.enums.Role;

public record UserAuthResponse(
        Integer id,
        String username,
        String email,
        String passwordHash,
        Role role
) {
}