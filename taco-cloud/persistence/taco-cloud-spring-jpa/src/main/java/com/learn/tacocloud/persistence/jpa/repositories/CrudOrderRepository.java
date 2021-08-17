package com.learn.tacocloud.persistence.jpa.repositories;

import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.repositories.OrderRepository;
import com.learn.tacocloud.persistence.jpa.entities.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CrudOrderRepository implements OrderRepository {
    private final SpringJpaCrudOrderRepository helperRepository;

    public CrudOrderRepository(SpringJpaCrudOrderRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Optional<Order> get(UUID id) {
        var orderEntity = helperRepository.findById(id);
        if (orderEntity.isPresent()) {
            return Optional.of(orderEntity.get().toOrder());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Order save(Order order) {
        return helperRepository.save(new OrderEntity(order)).toOrder();
    }
}
