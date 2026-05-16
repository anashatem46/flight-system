package org.learnjava.auth.client;

import org.learnjava.auth.dto.ApiResponse;
import org.learnjava.auth.dto.CreateUserRequest;
import org.learnjava.auth.dto.UserAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserServiceClient {

    private final RestClient restClient;

    public UserServiceClient(
            @Value("${services.user-service.base-url}") String userServiceBaseUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(userServiceBaseUrl)
                .build();
    }

    public void createUser(CreateUserRequest request) {
        restClient.post()
                .uri("/api/v1/users/internal")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    public UserAuthResponse findByEmail(String email) {
        ApiResponse<UserAuthResponse> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/users/internal/by-email")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (response == null || response.data() == null) {
            throw new IllegalStateException("User service returned an empty user auth response");
        }

        return response.data();
    }
}
