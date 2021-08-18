package com.learn.tacocloud.persistence.mongodb.repositories;

import com.learn.tacocloud.persistence.mongodb.entities.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongodbIngredientRepository extends CrudRepository<IngredientEntity, String> {
}
