package com.geekbrains.webapp.order.services;

import com.geekbrains.webapp.api.dtos.CartDto;
import com.geekbrains.webapp.api.dtos.OrderDetailsDto;
import com.geekbrains.webapp.api.dtos.OrderItemDto;
import com.geekbrains.webapp.api.dtos.UserDto;
import com.geekbrains.webapp.order.integration.CartServiceIntegration;
import com.geekbrains.webapp.order.integration.ProductServiceIntegration;
import com.geekbrains.webapp.order.integration.UserServiceIntegration;
import com.geekbrains.webapp.order.models.Order;
import com.geekbrains.webapp.order.models.OrderItem;
import com.geekbrains.webapp.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserServiceIntegration userService;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductServiceIntegration productService;

    @Transactional
    public void createOrder(OrderDetailsDto orderDetailsDto) {
        UserDto userDto = userService.getUserByName(orderDetailsDto.getUserName());
        CartDto cart = cartServiceIntegration.getUserCartDto(userDto.getName(), orderDetailsDto.getCartUid());
        Order order = new Order();
        order.setUserId(userDto.getId());
        order.setPrice(cart.getTotalPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(i.getPrice());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setQuantity(i.getQuantity());
            orderItem.setProductId(i.getProductId());
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cartServiceIntegration.clear(userDto.getName(),orderDetailsDto.getCartUid());
    }

    public List<Order> findAllByUsername(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }
}
