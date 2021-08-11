package com.learn.tacocloud.persistence.jdbc.repositories;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Raw implementation of {@link IngredientRepository} for
 * comparison with {@link JdbcIngredientRepository} to illustrate
 * the power of using {@link JdbcTemplate}.
 */
public class RawJdbcIngredientRepository implements IngredientRepository {
    private final DataSource dataSource;

    public RawJdbcIngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<Ingredient> get() {
        List<Ingredient> ingredients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(
                    "select id, name, type from Ingredient");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        IngredientType.valueOf(resultSet.getString("type")));
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            // ??? What should be done here ???
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return ingredients;
    }

    @Override
    public Optional<Ingredient> get(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(
                    "select id, name, type from Ingredient where id=?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Ingredient ingredient = null;
            if (resultSet.next()) {
                ingredient = new Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        IngredientType.valueOf(resultSet.getString("type")));
            }
            return Optional.of(ingredient);
        } catch (SQLException e) {
            // ??? What should be done here ???
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        throw new NotImplementedException();
    }
}
