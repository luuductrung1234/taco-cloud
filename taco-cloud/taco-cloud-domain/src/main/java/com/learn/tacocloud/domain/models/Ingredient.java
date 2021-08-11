package com.learn.tacocloud.domain.models;


import com.learn.tacocloud.domain.enums.IngredientType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final IngredientType type;
}
