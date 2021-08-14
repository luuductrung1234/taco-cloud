package com.learn.tacocloud.persistence.jpa.entities;

import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TACOS")
public class TacoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "CREATEDAT")
    private Date createdAt;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "TACO_INGREDIENTS",
            joinColumns = @JoinColumn(name = "TACOID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENTID", nullable = false))
    private List<IngredientEntity> ingredients;

    @ManyToMany(mappedBy = "tacos")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<OrderEntity> orders;

    public TacoEntity(final Taco taco) {
        this.id = taco.getId();
        this.name = taco.getName();
        this.ingredients = taco.getIngredients().stream().map(IngredientEntity::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Taco toTaco() {
        return new Taco(id, name, ingredients.stream().map(IngredientEntity::toIngredient).collect(Collectors.toList()), createdAt);
    }
}
