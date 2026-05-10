package org.learnjava.flightsystem.user.service;

import org.learnjava.flightsystem.user.DTO.UserDto;
import org.learnjava.flightsystem.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> addUser(UserDto userDto);

    UserDto getUserById(Integer userId);

    Optional<UserDto> getUserByEmail(String email);

    List<UserDto> getUsers();

    User authenticate(String username, String password);


}
