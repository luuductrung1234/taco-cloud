package com.learn.tacocloud.web.data;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TacoRepositoryTests {
    @Autowired
    TacoRepository tacoRepository;

    @Test
    public void saveTacoWithThreeIngredients() {
        var taco = new Taco();
        taco.setName("Taco One");
        taco.addIngredient(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
        taco.addIngredient(new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
        taco.addIngredient(new Ingredient("CHED", "Shredded Cheddar", IngredientType.CHEESE));
        var savedTaco = tacoRepository.save(taco);

        var fetchedTaco = tacoRepository.get(savedTaco.getId()).get();
        Assertions.assertThat(fetchedTaco.getName()).isEqualTo("Taco One");
        Assertions.assertThat(fetchedTaco.getCreatedAt().getTime()).isEqualTo(savedTaco.getCreatedAt().getTime());

        var ingredients = fetchedTaco.getIngredients();
        Assertions.assertThat(ingredients.size()).isEqualTo(3);
    }
}
