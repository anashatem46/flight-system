package org.learnjava.flightsystem.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.user.dto.CreateUserRequest;
import org.learnjava.flightsystem.user.dto.UserAuthResponse;
import org.learnjava.flightsystem.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.learnjava.flightsystem.user.api.ApiResponseUtils.createdWithoutData;
import static org.learnjava.flightsystem.user.api.ApiResponseUtils.success;

@RestController
@RequestMapping("/api/v1/users/internal")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createUserForAuth(
            @Valid @RequestBody CreateUserRequest request
    ) {
        userService.createUserForAuth(request);
        return createdWithoutData("User created successfully");
    }

    @GetMapping("/by-email")
    public ResponseEntity<ApiResponse<UserAuthResponse>> getUserByEmailForAuth(
            @RequestParam String email
    ) {
        UserAuthResponse user = userService.getUserByEmailForAuth(email);
        return success("User fetched successfully", user);
    }
}
