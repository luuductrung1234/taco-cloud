package com.learn.tacoclouddomain.repositories;

import com.learn.tacoclouddomain.models.Ingredient;

import java.util.List;

public interface IngredientRepository {
    List<Ingredient> get();
    Ingredient get(String id);
}
