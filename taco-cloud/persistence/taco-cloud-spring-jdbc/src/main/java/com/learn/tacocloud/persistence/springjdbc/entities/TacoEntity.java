package com.learn.tacocloud.persistence.springjdbc.entities;

import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Table("TACOS")
@AllArgsConstructor
@NoArgsConstructor
public class TacoEntity {
    @Id
    private UUID id;

    private String name;

    @MappedCollection(idColumn = "TACOID", keyColumn = "TACOKEY")
    private List<IngredientRef> ingredientRefs;

    @Column("CREATEDAT")
    private Date createdAt;

    public TacoEntity(final Taco taco) {
        this.id = taco.getId();
        this.name = taco.getName();
        this.ingredientRefs = taco.getIngredients().stream().map(IngredientRef::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Taco toTaco(List<Ingredient> ingredients) {
        return new Taco(id, name, ingredients, createdAt);
    }
}
