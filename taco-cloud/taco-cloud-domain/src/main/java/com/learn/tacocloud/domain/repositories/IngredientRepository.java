package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> get();
    Optional<Ingredient> get(String id);
    Ingredient save(Ingredient ingredient);
}
