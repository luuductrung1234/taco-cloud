package com.learn.tacocloud.web.converters;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class IngredientByIdConverterTest {
    private final Ingredient FOUNDED_INGREDIENT = new Ingredient("AAAA", "TEST INGREDIENT", IngredientType.CHEESE);

    private IngredientByIdConverter converter;

    @BeforeEach
    public void setup() {
        var ingredientRepository = Mockito.mock(IngredientRepository.class);
        Mockito.when(ingredientRepository.get("AAAA"))
                .thenReturn(Optional.of(FOUNDED_INGREDIENT));
        Mockito.when(ingredientRepository.get("ZZZZ"))
                .thenReturn(Optional.empty());

        this.converter = new IngredientByIdConverter(ingredientRepository);
    }

    @Test
    public void shouldReturnValueWhenPresent() {
        Assertions.assertThat(converter.convert("AAAA")).isEqualTo(FOUNDED_INGREDIENT);
    }

    @Test
    public void shouldReturnNullWhenMissing() {
        Assertions.assertThat(converter.convert("ZZZZ")).isNull();
    }
}
