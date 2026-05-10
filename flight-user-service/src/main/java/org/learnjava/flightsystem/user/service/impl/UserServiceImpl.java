package org.learnjava.flightsystem.user.service.impl;

import lombok.AllArgsConstructor;
import org.learnjava.flightsystem.user.DTO.UserDto;
import org.learnjava.flightsystem.user.entity.User;
import org.learnjava.flightsystem.user.exceptions.UserApiException;
import org.learnjava.flightsystem.user.mapper.UserMapper;
import org.learnjava.flightsystem.user.repo.UserRepo;
import org.learnjava.flightsystem.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public Optional<UserDto> addUser(UserDto userDto) {
        String password = userDto.password();
        if (password == null || password.isBlank()) {
            throw new UserApiException("password Can't be empty", HttpStatus.BAD_REQUEST);
        }
        var hashedPassword = passwordEncoder.encode(password);

        var udto = new UserDto(userDto.id(), userDto.email(), userDto.username(), hashedPassword);

        User savedUser = userRepo.save(userMapper.convertToUserEntity(udto));

        return Optional.of(userMapper.convertToUserDto(savedUser));

    }

    @Override
    public UserDto getUserById(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.map(userMapper::convertToUserDto)
                .orElseThrow(() -> new UserApiException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(userMapper::convertToUserDto);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::convertToUserDto)
                .toList();
    }

    @Override
    public User authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return userRepo.findByUsername(username).orElseThrow();
    }
}

