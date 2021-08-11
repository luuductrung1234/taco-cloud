package com.learn.tacocloud.persistence.jdbc.repositories;

import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        var tacoId = saveTaco(taco);
        taco.setId(tacoId);
        for (var ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTaco(Taco taco) {
        taco.setCreatedAt(new Date());

        var params = Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime()));
        var preparedStatementCreatorFactory = new PreparedStatementCreatorFactory("INSERT INTO Tacos (name, createdAt) VALUES (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        var preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(params);

        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbcTemplate.update("INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?, ?)", tacoId, ingredient.getId());
    }
}
