package com.learn.tacocloud.persistence.springjdbc.repositories;

import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.repositories.OrderRepository;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import com.learn.tacocloud.persistence.springjdbc.entities.OrderEntity;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CrudOrderRepository implements OrderRepository {
    private final SpringJdbcCrudOrderRepository helperRepository;
    private final TacoRepository tacoRepository;

    public CrudOrderRepository(SpringJdbcCrudOrderRepository helperRepository, TacoRepository tacoRepository) {
        this.helperRepository = helperRepository;
        this.tacoRepository = tacoRepository;
    }

    @Override
    public Optional<Order> get(Long id) {
        var orderEntity = helperRepository.findById(id);
        if (orderEntity.isPresent()) {
            var order = attachTaco(new ArrayList<>() {{
                add(orderEntity.get());
            }}).stream().findFirst();
            return order;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Order save(Order order) {
        var savedOrder = helperRepository.save(new OrderEntity(order));
        order.setId(savedOrder.getId());
        order.setCreatedAt(savedOrder.getCreatedAt());
        return order;
    }

    private List<Order> attachTaco(List<OrderEntity> orderEntities) {
        var tacos = IterableUtils.toList(tacoRepository.get());
        var result = new ArrayList<Order>();
        for (var orderEntity : orderEntities) {
            var tacoIds = orderEntity.getTacoRefs().stream().map(i -> i.getTacoId()).collect(Collectors.toList());
            var attachedTacos = tacos.stream().filter(i -> tacoIds.contains(i.getId())).collect(Collectors.toList());
            var order = orderEntity.toOrder(attachedTacos);
            result.add(order);
        }
        return result;
    }
}
