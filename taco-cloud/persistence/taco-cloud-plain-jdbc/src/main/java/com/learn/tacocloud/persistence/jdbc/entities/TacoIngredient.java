package com.learn.tacocloud.persistence.jdbc.entities;

import com.learn.tacocloud.domain.enums.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TacoIngredient {
    private UUID id;
    private String name;
    private Date createdAt;
    private String ingredientId;
    private String ingredientName;
    private IngredientType ingredientType;
}
