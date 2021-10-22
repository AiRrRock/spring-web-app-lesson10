package com.geekbrains.webapp.order.repositories;

import com.geekbrains.webapp.order.models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.userId = :userId")
    @EntityGraph(value = "orders.for-front")
    List<Order> findAllByUserId(Long userId);
}
