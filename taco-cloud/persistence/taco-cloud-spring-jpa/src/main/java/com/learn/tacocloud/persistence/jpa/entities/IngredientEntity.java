package com.learn.tacocloud.persistence.jpa.entities;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INGREDIENTS")
public class IngredientEntity {
    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType type;

    @ManyToMany(mappedBy = "ingredients")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<TacoEntity> tacos;

    public IngredientEntity(final Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

    public Ingredient toIngredient() {
        return new Ingredient(id, name, type);
    }
}
