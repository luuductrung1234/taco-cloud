package com.learn.tacocloud.domain.models;


import com.learn.tacocloud.domain.enums.IngredientType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {
    private String id;
    private String name;
    private IngredientType type;
}
