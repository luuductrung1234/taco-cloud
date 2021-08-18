package com.learn.tacocloud.persistence.mongodb.entities;

import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tacos")
public class TacoEntity {
    @Id
    private String id;
    private String name;
    private Date createdAt;
    private List<IngredientUDT> ingredients;

    public TacoEntity(final Taco taco) {
        this.id = UUID.randomUUID().toString();
        this.name = taco.getName();
        this.ingredients = taco.getIngredients().stream().map(IngredientUDT::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Taco toTaco() {
        return new Taco(UUID.fromString(id), name, ingredients.stream().map(IngredientUDT::toIngredient).collect(Collectors.toList()), createdAt);
    }
}
