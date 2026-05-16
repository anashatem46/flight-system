package org.learnjava.auth.client;

import org.learnjava.auth.dto.ApiResponse;
import org.learnjava.auth.dto.CreateUserRequest;
import org.learnjava.auth.dto.UserAuthResponse;
import org.learnjava.auth.exception.DuplicateUserException;
import org.learnjava.auth.exception.UserNotFoundException;
import org.learnjava.auth.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class UserServiceClient {

    private static final String INTERNAL_TOKEN_HEADER = "X-Internal-Token";

    private final RestClient restClient;
    private final String internalToken;

    public UserServiceClient(
            @Value("${services.user-service.base-url}") String userServiceBaseUrl,
            @Value("${internal.token}") String internalToken
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(userServiceBaseUrl)
                .build();
        this.internalToken = internalToken;
    }

    public void createUser(CreateUserRequest request) {
        try {
            restClient.post()
                    .uri("/api/v1/users/internal")
                    .header(INTERNAL_TOKEN_HEADER, internalToken)
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();

        } catch (HttpClientErrorException.BadRequest exception) {
            throw new DuplicateUserException();
        } catch (RestClientResponseException exception) {
            throw new UserServiceException("User service request failed", exception);
        }
    }

    public UserAuthResponse findByEmail(String email) {
        try {
            ApiResponse<UserAuthResponse> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/v1/users/internal/by-email")
                            .queryParam("email", email)
                            .build())
                    .header(INTERNAL_TOKEN_HEADER, internalToken)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            if (response == null || response.data() == null) {
                throw new UserServiceException("User service returned empty response");
            }

            return response.data();

        } catch (HttpClientErrorException.NotFound exception) {
            throw new UserNotFoundException();
        } catch (RestClientResponseException exception) {
            throw new UserServiceException("User service request failed", exception);
        }
    }
}
