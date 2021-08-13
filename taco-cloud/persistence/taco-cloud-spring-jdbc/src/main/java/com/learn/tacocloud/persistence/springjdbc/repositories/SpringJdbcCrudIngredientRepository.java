package com.learn.tacocloud.persistence.springjdbc.repositories;

import com.learn.tacocloud.persistence.springjdbc.entities.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJdbcCrudIngredientRepository extends CrudRepository<IngredientEntity, String> {
}
