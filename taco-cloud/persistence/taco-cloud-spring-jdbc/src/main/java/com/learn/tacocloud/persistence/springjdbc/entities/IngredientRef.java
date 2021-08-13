package com.learn.tacocloud.persistence.springjdbc.entities;

import com.learn.tacocloud.domain.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("TACO_INGREDIENTS")
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRef {
    @Id
    private Long id;

    @Column("INGREDIENTID")
    private String ingredientId;

    public IngredientRef(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
    }
}
