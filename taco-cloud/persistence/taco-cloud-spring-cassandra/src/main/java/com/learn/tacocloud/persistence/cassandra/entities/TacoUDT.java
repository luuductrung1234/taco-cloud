package com.learn.tacocloud.persistence.cassandra.entities;

import com.learn.tacocloud.domain.models.Taco;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType("taco")
public class TacoUDT {
    private UUID id;
    private String name;
    private List<IngredientUDT> ingredients;
    private Date createdAt;

    public TacoUDT(final Taco taco) {
        this.id = taco.getId();
        this.name = taco.getName();
        this.ingredients = taco.getIngredients().stream().map(IngredientUDT::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Taco toTaco() {
        return new Taco(id, name, ingredients.stream().map(IngredientUDT::toIngredient).collect(Collectors.toList()), createdAt);
    }
}
