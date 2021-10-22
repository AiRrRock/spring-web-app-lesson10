package com.geekbrains.webapp.order.integration;

import com.geekbrains.webapp.api.dtos.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    public CartDto getUserCartDto(String userName, String cartUuid) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", userName);
        return restTemplate.exchange(cartServiceUrl + "/api/v1/cart/" + cartUuid, HttpMethod.GET, new HttpEntity(headers), CartDto.class).getBody();
    }

    public void clear(String userName, String cartUuid) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", userName);
        restTemplate.exchange(cartServiceUrl + "/api/v1/cart/" + cartUuid + "/clear", HttpMethod.GET, new HttpEntity(headers), void.class);
    }
}
