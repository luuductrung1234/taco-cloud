package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> get(UUID id);

    Order save(Order order);
}
