package org.learnjava.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.learnjava.auth.client.UserServiceClient;
import org.learnjava.auth.config.JwtService;
import org.learnjava.auth.dto.*;
import org.learnjava.auth.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String TOKEN_TYPE = "Bearer";

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final UserServiceClient userServiceClient;

    @Override
    public AuthResponse register(RegisterRequest request) {
        String passwordHash =  passwordEncoder.encode(request.password());

        CreateUserRequest createUserRequest = new CreateUserRequest(
                request.username(),
                request.email(),
                passwordHash,
                DEFAULT_ROLE
        );
        userServiceClient.createUser(createUserRequest);
        UserAuthResponse user = userServiceClient.findByEmail(request.email());
        String token = jwtService.generateToken(user);

        return new AuthResponse(token,TOKEN_TYPE);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserAuthResponse user = userServiceClient.findByEmail(request.email());

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, TOKEN_TYPE);
    }
}
