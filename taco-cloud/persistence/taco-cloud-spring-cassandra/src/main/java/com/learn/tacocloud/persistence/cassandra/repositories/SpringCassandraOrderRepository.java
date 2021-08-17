package com.learn.tacocloud.persistence.cassandra.repositories;

import com.learn.tacocloud.persistence.cassandra.entities.OrderEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringCassandraOrderRepository extends CassandraRepository<OrderEntity, UUID> {
}
