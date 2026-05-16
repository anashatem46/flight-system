package org.learnjava.flightsystem.user.service;

import org.learnjava.flightsystem.user.dto.CreateUserRequest;
import org.learnjava.flightsystem.user.dto.UserAuthResponse;
import org.learnjava.flightsystem.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Integer userId);

    UserDto getUserByEmail(String email);

    List<UserDto> getUsers();

    UserDto updateUser(Integer userId, UserDto userDto);

    void deleteUser(Integer userId);

    UserAuthResponse createUserForAuth(CreateUserRequest request);

    UserAuthResponse getUserByEmailForAuth(String email);
}
