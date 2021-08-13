package com.learn.tacocloud.persistence.springjdbc.repositories;

import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.repositories.OrderRepository;
import com.learn.tacocloud.persistence.springjdbc.entities.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CrudOrderRepository implements OrderRepository {
    private final SpringJdbcCrudOrderRepository helperRepository;

    public CrudOrderRepository(SpringJdbcCrudOrderRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Order save(Order order) {
        var savedOrder = helperRepository.save(new OrderEntity(order));
        order.setId(savedOrder.getId());
        return order;
    }
}
