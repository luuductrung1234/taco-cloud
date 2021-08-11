package com.learn.tacocloud.persistence.jdbc.repositories;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> get() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredients",
                this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> get(String id) {
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from Ingredients where id=?",
                this::mapRowToIngredient,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredients (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
            throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                IngredientType.valueOf(row.getString("type")));
    }
}
