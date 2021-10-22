package com.geekbrains.webapp.order.controllers;

import com.geekbrains.webapp.api.dtos.OrderDetailsDto;
import com.geekbrains.webapp.api.dtos.OrderDto;
import com.geekbrains.webapp.order.integration.UserServiceIntegration;
import com.geekbrains.webapp.order.services.OrderService;
import com.geekbrains.webapp.order.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final UserServiceIntegration userService;

    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(orderDetailsDto);
    }

    @GetMapping("/{userName}")
    public List<OrderDto> getOrdersForCurrentUser(@PathVariable String userName) {
        return orderService.findAllByUsername(userService.getUserByName(userName).getId()).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }
}
