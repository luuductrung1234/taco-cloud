package com.learn.tacocloud.web.controllers;


import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DesignTacoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredientRepository ingredientRepository;

    private List<Ingredient> ingredients;

    @BeforeEach
    public void setUp() {
        ingredients = IterableUtils.toList(ingredientRepository.get());
    }

    @Test
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/design"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("design"))
                .andExpect(MockMvcResultMatchers.model().attribute("wrap", ingredients.subList(0, 2)))
                .andExpect(MockMvcResultMatchers.model().attribute("protein", ingredients.subList(2, 4)))
                .andExpect(MockMvcResultMatchers.model().attribute("veggies", ingredients.subList(4, 6)))
                .andExpect(MockMvcResultMatchers.model().attribute("cheese", ingredients.subList(6, 8)))
                .andExpect(MockMvcResultMatchers.model().attribute("sauce", ingredients.subList(8, 10)));
    }

    @Test
    public void processTaco() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/design")
                .content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().stringValues("Location", "/orders/current"));
    }
}
