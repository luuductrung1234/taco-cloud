package com.learn.tacocloud.persistence.jpa.repositories;

import com.learn.tacocloud.persistence.jpa.entities.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaCrudIngredientRepository extends CrudRepository<IngredientEntity, String> {
}
