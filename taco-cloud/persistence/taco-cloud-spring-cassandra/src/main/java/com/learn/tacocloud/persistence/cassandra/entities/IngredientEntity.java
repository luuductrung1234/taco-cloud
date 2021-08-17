package com.learn.tacocloud.persistence.cassandra.entities;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("INGREDIENTS")
public class IngredientEntity {
    @PrimaryKey
    private String id;

    private String name;

    private IngredientType type;

    public IngredientEntity(final Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

    public Ingredient toIngredient() {
        return new Ingredient(id, name, type);
    }
}
