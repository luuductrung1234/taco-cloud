package com.learn.tacoclouddomain.models;


import com.learn.tacoclouddomain.enums.IngredientType;
import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final IngredientType type;
}
