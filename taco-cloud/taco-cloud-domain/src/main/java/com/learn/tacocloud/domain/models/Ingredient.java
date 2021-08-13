package com.learn.tacocloud.domain.models;


import com.learn.tacocloud.domain.enums.IngredientType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    private final String id;
    private final String name;
    private final IngredientType type;
}
