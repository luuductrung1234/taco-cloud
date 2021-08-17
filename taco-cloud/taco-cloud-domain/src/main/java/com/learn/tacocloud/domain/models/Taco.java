package com.learn.tacocloud.domain.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"createdAt"})
@AllArgsConstructor
@NoArgsConstructor
public class Taco {
    private UUID id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    private Date createdAt;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
