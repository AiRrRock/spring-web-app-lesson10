package com.geekbrains.webapp.order.utils;

import com.geekbrains.webapp.api.dtos.OrderDto;
import com.geekbrains.webapp.api.dtos.OrderItemDto;
import com.geekbrains.webapp.order.integration.ProductServiceIntegration;
import com.geekbrains.webapp.order.models.Order;
import com.geekbrains.webapp.order.models.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {
@Autowired
private ProductServiceIntegration productService;


    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProductId(), productService.getProductById(orderItem.getProductId()).getTitle() ,orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }

    public OrderDto orderToDto(Order order) {
        return new OrderDto(order.getId(), order.getItems().stream().map(oi -> orderItemToDto(oi)).collect(Collectors.toList()), order.getAddress(), order.getPhone(), order.getPrice());
    }
}
