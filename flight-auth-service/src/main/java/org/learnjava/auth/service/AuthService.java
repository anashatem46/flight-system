package org.learnjava.auth.service;

import org.learnjava.auth.dto.AuthResponse;
import org.learnjava.auth.dto.LoginRequest;
import org.learnjava.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}

