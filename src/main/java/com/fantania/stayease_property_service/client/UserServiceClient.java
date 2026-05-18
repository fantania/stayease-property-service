package com.fantania.stayease_property_service.client;

import com.fantania.stayease_property_service.dto.ServiceTokenRequest;
import com.fantania.stayease_property_service.dto.ServiceTokenResponse;
import com.fantania.stayease_property_service.dto.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserServiceClient {

    private final RestClient restClient;

    @Value("${app.services.user-service.base-url}")
    private String userServiceBaseUrl;

    @Value("${app.service-auth.client-id}")
    private String clientId;

    @Value("${app.service-auth.client-secret}")
    private String clientSecret;

    public UserServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public UserResponse getUserById(Long userId) {
        String token = getServiceToken();

        return restClient.get()
                .uri(userServiceBaseUrl + "/api/v1/users/" + userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(UserResponse.class);
    }

    private String getServiceToken() {
        ServiceTokenRequest request = new ServiceTokenRequest(
                clientId,
                clientSecret
        );

        ServiceTokenResponse response = restClient.post()
                .uri(userServiceBaseUrl + "/api/v1/service-auth/token")
                .body(request)
                .retrieve()
                .body(ServiceTokenResponse.class);

        if (response == null || response.accessToken() == null) {
            throw new RuntimeException("Unable to retrieve service token from user-service");
        }

        return response.accessToken();
    }
}
