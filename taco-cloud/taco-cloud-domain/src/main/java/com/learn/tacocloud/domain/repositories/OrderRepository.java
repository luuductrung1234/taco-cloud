package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> get(Long id);

    Order save(Order order);
}
