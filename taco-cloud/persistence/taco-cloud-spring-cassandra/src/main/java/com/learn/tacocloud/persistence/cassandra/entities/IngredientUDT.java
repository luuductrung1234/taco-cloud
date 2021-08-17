package com.learn.tacocloud.persistence.cassandra.entities;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType("ingredient")
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
