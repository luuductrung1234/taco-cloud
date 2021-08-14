package com.learn.tacocloud.persistence.jdbc.repositories;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Taco> get() {
        var sql = "SELECT " +
                "Tacos.Id, Tacos.Name, Tacos.CreatedAt, " +
                "Ingredients.Id AS IngredientId, Ingredients.Name AS IngredientName, Ingredients.Type AS IngredientType " +
                "FROM Tacos " +
                "LEFT OUTER JOIN Taco_Ingredients ON Tacos.Id = Taco_Ingredients.TacoId " +
                "LEFT OUTER JOIN Ingredients ON Ingredients.Id = Taco_Ingredients.IngredientId";

        var tacoIngredients = jdbcTemplate.query(sql, this::mapRowToTacoIngredient);
        return mapToTaco(tacoIngredients);
    }

    @Override
    public Optional<Taco> get(Long id) {
        var sql = "SELECT " +
                "Tacos.Id, Tacos.Name, Tacos.CreatedAt, " +
                "Ingredients.Id AS IngredientId, Ingredients.Name AS IngredientName, Ingredients.Type AS IngredientType " +
                "FROM Tacos " +
                "LEFT OUTER JOIN Taco_Ingredients ON Tacos.Id = Taco_Ingredients.TacoId " +
                "LEFT OUTER JOIN Ingredients ON Ingredients.Id = Taco_Ingredients.IngredientId " +
                "WHERE Tacos.Id=?";

        var tacoIngredients = jdbcTemplate.query(sql, this::mapRowToTacoIngredient, id);
        return mapToTaco(tacoIngredients).stream().findFirst();
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

    private List<Taco> mapToTaco(List<TacoIngredient> tacoIngredients) {
        var result = new ArrayList<Taco>();

        if (tacoIngredients.isEmpty())
            return result;

        var groupedTacos = tacoIngredients.stream().collect(Collectors.groupingBy(TacoIngredient::getId));
        for (var groupedTaco : groupedTacos.entrySet()) {
            var tacoIngredient = groupedTaco.getValue().stream().findFirst().get();
            var ingredients = groupedTaco.getValue().stream().map(i -> new Ingredient(i.getIngredientId(), i.getIngredientName(), i.getIngredientType())).collect(Collectors.toList());
            var taco = new Taco(tacoIngredient.getId(), tacoIngredient.getIngredientName(), ingredients, tacoIngredient.getCreatedAt());
            result.add(taco);
        }
        return result;
    }

    private TacoIngredient mapRowToTacoIngredient(ResultSet row, int rowNum) throws SQLException {
        return new TacoIngredient(
                row.getLong("Id"),
                row.getString("Name"),
                row.getDate("CreatedAt"),
                row.getString("IngredientId"),
                row.getString("IngredientName"),
                IngredientType.valueOf(row.getString("IngredientType")));
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

@Data
@AllArgsConstructor
class TacoIngredient {
    private Long id;
    private String name;
    private Date createdAt;
    private String ingredientId;
    private String ingredientName;
    private IngredientType ingredientType;
}
