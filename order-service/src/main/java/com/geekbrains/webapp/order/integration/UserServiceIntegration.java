package com.geekbrains.webapp.order.integration;

import com.geekbrains.webapp.api.dtos.ProductDto;
import com.geekbrains.webapp.api.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.user-service.url}")
    private String userServiceUrl;

    public UserDto getUserByName(String userName) {
        return restTemplate.getForObject(userServiceUrl + "/api/v1/users/" + userName, UserDto.class);
    }
}
