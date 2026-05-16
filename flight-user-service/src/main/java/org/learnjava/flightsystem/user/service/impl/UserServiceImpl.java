package org.learnjava.flightsystem.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.learnjava.flightsystem.user.dto.CreateUserRequest;
import org.learnjava.flightsystem.user.dto.UserAuthResponse;
import org.learnjava.flightsystem.user.dto.UserDto;
import org.learnjava.flightsystem.user.entity.User;
import org.learnjava.flightsystem.user.exceptions.UserApiException;
import org.learnjava.flightsystem.user.mapper.UserMapper;
import org.learnjava.flightsystem.user.repo.UserRepo;
import org.learnjava.flightsystem.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        if (userRepo.existsByEmail(userDto.email())) {
            throw new UserApiException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.convertToUserEntity(userDto);
        User savedUser = userRepo.save(user);

        return userMapper.convertToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserApiException("User not found", HttpStatus.NOT_FOUND));

        return userMapper.convertToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserApiException("User not found", HttpStatus.NOT_FOUND));

        return userMapper.convertToUserDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::convertToUserDto)
                .toList();
    }

    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new UserApiException("User not found", HttpStatus.NOT_FOUND));

        if (!existingUser.getEmail().equals(userDto.email())
                && userRepo.existsByEmail(userDto.email())) {
            throw new UserApiException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        existingUser.setEmail(userDto.email());
        existingUser.setUsername(userDto.username());

        User updatedUser = userRepo.save(existingUser);

        return userMapper.convertToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new UserApiException("User not found", HttpStatus.NOT_FOUND));

        userRepo.delete(existingUser);
    }

    @Override
    public UserAuthResponse createUserForAuth(CreateUserRequest request) {
        if (userRepo.existsByEmail(request.email())) {
            throw new UserApiException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        if (userRepo.existsByUsername(request.username())) {
            throw new UserApiException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(request.passwordHash())
                .role(request.role())
                .build();

        User savedUser = userRepo.save(user);

        return toUserAuthResponse(savedUser);
    }

    @Override
    public UserAuthResponse getUserByEmailForAuth(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserApiException("User not found", HttpStatus.NOT_FOUND));

        return toUserAuthResponse(user);
    }

    private UserAuthResponse toUserAuthResponse(User user) {
        return new UserAuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole()
        );
    }
}
