package com.learn.tacocloud.web.converters;

import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.get(id).get();
    }
}
