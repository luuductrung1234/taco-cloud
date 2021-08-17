package com.learn.tacocloud.persistence.jpa.repositories;

import com.learn.tacocloud.persistence.jpa.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SpringJpaCrudOrderRepository extends CrudRepository<OrderEntity, UUID> {
    List<OrderEntity> findByDeliveryZip(String deliveryZip);

    List<OrderEntity> findByDeliveryZipOrderByCreatedAt(String deliveryZip);

    @Query("select o from ORDERS o where o.deliveryCity='Seattle'")
    List<OrderEntity> readOrdersDeliveredInSeattle();

    List<OrderEntity> readOrdersByDeliveryZipAndCreatedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<OrderEntity> findByDeliveryStreetAndDeliveryCityIgnoreCase(String deliveryZip, String deliveryCity);
}
