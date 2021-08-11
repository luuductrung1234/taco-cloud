package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Order;

public interface OrderRepository {
    Order save(Order order);
}
