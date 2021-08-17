package com.learn.tacocloud.persistence.cassandra.entities;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("TACOS")
public class TacoEntity {
    /*
    solo key column require method findByIdAndCreatedAt to get single taco
    https://stackoverflow.com/questions/64720353/spring-data-cassandra-throws-cannot-obtain-where-clauses-for-entity

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt;
     */

    @PrimaryKey
    private UUID id;

    @Column("CREATEDAT")
    private Date createdAt;

    private String name;

    @Column("INGREDIENTS")
    private List<IngredientUDT> ingredients;

    public TacoEntity(final Taco taco) {
        this.id = Uuids.timeBased();
        this.name = taco.getName();
        this.ingredients = taco.getIngredients().stream().map(IngredientUDT::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Taco toTaco() {
        return new Taco(id, name, ingredients.stream().map(IngredientUDT::toIngredient).collect(Collectors.toList()), createdAt);
    }
}
