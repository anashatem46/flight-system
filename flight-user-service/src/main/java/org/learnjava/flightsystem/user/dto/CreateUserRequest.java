package org.learnjava.flightsystem.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.learnjava.flightsystem.user.enums.Role;

public record CreateUserRequest(

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password hash is required")
        String passwordHash,

        @NotNull(message = "Role is required")
        Role role
) {
}