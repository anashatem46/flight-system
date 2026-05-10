package org.learnjava.flightsystem.user.DTO;

public record UserDto(
        Integer id, String email,
        String username,
        String password) {
}