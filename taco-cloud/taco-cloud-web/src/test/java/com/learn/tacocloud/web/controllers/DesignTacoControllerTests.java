package com.learn.tacocloud.web.controllers;


import com.learn.tacocloud.domain.enums.IngredientType;
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
import java.util.stream.Collectors;

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
                .andExpect(MockMvcResultMatchers.model().attribute("wrap", ingredients.stream().filter(i -> i.getType() == IngredientType.WRAP).collect(Collectors.toList())))
                .andExpect(MockMvcResultMatchers.model().attribute("protein", ingredients.stream().filter(i -> i.getType() == IngredientType.PROTEIN).collect(Collectors.toList())))
                .andExpect(MockMvcResultMatchers.model().attribute("veggies", ingredients.stream().filter(i -> i.getType() == IngredientType.VEGGIES).collect(Collectors.toList())))
                .andExpect(MockMvcResultMatchers.model().attribute("cheese", ingredients.stream().filter(i -> i.getType() == IngredientType.CHEESE).collect(Collectors.toList())))
                .andExpect(MockMvcResultMatchers.model().attribute("sauce", ingredients.stream().filter(i -> i.getType() == IngredientType.SAUCE).collect(Collectors.toList())));
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
