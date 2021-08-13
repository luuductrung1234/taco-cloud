package com.learn.tacocloud.persistence.springjdbc.entities;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("INGREDIENTS")
@AllArgsConstructor
@NoArgsConstructor
public class IngredientEntity {
    @Id
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
