package com.learn.tacocloud.persistence.cassandra.repositories;

import com.learn.tacocloud.persistence.cassandra.entities.IngredientEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringCassandraIngredientRepository extends CassandraRepository<IngredientEntity, String> {
}
