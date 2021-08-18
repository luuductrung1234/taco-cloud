package com.learn.tacocloud.persistence.mongodb.seedworks;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.persistence.mongodb.entities.IngredientEntity;
import com.learn.tacocloud.persistence.mongodb.repositories.SpringMongodbIngredientRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {
    private final SpringMongodbIngredientRepository ingredientRepository;

    public DataLoader(SpringMongodbIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!IterableUtils.isEmpty(ingredientRepository.findAll()))
            return;

        var ingredients = Arrays.asList(
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

        ingredientRepository.saveAll(ingredients.stream().map(IngredientEntity::new).collect(Collectors.toList()));
    }
}
