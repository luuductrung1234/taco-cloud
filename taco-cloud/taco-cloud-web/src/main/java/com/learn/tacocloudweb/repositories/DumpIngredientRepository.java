package com.learn.tacocloudweb.repositories;

import com.learn.tacoclouddomain.enums.IngredientType;
import com.learn.tacoclouddomain.models.Ingredient;
import com.learn.tacoclouddomain.repositories.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class DumpIngredientRepository implements IngredientRepository {
    List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
            new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
            new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
            new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
            new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
            new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
            new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
            new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE)
    );

    @Override
    public List<Ingredient> get() {
        return ingredients;
    }

    @Override
    public Ingredient get(String id) {
        return ingredients.stream().filter(i -> i.getId().equals(id)).findFirst().get();
    }
}
