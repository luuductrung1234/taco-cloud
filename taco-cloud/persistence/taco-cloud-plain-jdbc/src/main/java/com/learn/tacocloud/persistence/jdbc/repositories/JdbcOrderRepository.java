package com.learn.tacocloud.persistence.jdbc.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInserter;
    private final SimpleJdbcInsert tacoInserter;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Orders")
                .usingGeneratedKeyColumns("id");
        this.tacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Order_Tacos");
    }

    @Override
    public Optional<Order> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        var orderId = saveOrderDetails(order);
        order.setId(UUID.fromString(orderId));
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private String saveOrderDetails(Order order) {
        order.setCreatedAt(new Date());
        var objectMapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("createdAt", order.getCreatedAt());

        var orderId = String.valueOf(orderInserter.executeAndReturnKeyHolder(values).getKeyList().get(0).get("id"));
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, String orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        tacoInserter.execute(values);
    }
}
