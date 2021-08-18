package com.learn.tacocloud.persistence.mongodb.entities;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientUDT {
    private String id;
    private String name;
    private IngredientType type;

    public IngredientUDT(final Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

    public Ingredient toIngredient() {
        return new Ingredient(id, name, type);
    }
}
