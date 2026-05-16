package org.learnjava.auth.config;

import lombok.RequiredArgsConstructor;
import org.learnjava.auth.client.UserServiceClient;
import org.learnjava.auth.dto.UserAuthResponse;
import org.learnjava.auth.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserAuthResponse user = userServiceClient.findByEmail(email);

            return User.builder()
                    .username(user.email())
                    .password(user.passwordHash())
                    .authorities(user.role())
                    .build();

        } catch (UserNotFoundException exception) {
            throw new UsernameNotFoundException("User not found with email: " + email, exception);
        }
    }

}
