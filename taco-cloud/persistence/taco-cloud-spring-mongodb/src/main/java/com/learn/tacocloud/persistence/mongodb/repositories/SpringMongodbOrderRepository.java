package com.learn.tacocloud.persistence.mongodb.repositories;

import com.learn.tacocloud.persistence.mongodb.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongodbOrderRepository extends CrudRepository<OrderEntity, String> {
}
