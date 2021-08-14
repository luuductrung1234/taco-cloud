package com.learn.tacocloud.web.data;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@SpringBootTest
public class IngredientRepositoryTests {
    @Autowired
    IngredientRepository ingredientRepo;

    @Autowired
    JdbcTemplate jdbc;

    @Test
    public void findById() {
        Optional<Ingredient> flto = ingredientRepo.get("FLTO");
        Assertions.assertThat(flto.isPresent()).isTrue();
        Assertions.assertThat(flto.get()).isEqualTo(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));

        Optional<Ingredient> xxxx = ingredientRepo.get("XXXX");
        Assertions.assertThat(xxxx.isEmpty()).isTrue();

    }
}
