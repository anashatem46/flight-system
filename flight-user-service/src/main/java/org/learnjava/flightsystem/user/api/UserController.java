package org.learnjava.flightsystem.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.user.dto.UserDto;
import org.learnjava.flightsystem.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.learnjava.flightsystem.user.api.ApiResponseUtils.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(
            @Valid @RequestBody UserDto userDto
    ) {
        UserDto createdUser = userService.createUser(userDto);
        return created("User created successfully", createdUser);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getUsers();
        return success("Users fetched successfully", users);
    }

    @GetMapping("/by-email")
    public ResponseEntity<ApiResponse<UserDto>> getUserByEmail(
            @RequestParam String email
    ) {
        UserDto user = userService.getUserByEmail(email);
        return success("User fetched successfully", user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @PathVariable Integer id
    ) {
        UserDto user = userService.getUserById(id);
        return success("User fetched successfully", user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserDto userDto
    ) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return success("User updated successfully", updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Integer id
    ) {
        userService.deleteUser(id);
        return successWithoutData("User deleted successfully");
    }
}