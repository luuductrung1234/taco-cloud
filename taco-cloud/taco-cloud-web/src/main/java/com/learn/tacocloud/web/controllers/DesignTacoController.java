package com.learn.tacocloud.web.controllers;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Account;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.AccountRepository;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final AccountRepository accountRepository;

    public DesignTacoController(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository,
            AccountRepository accountRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.accountRepository = accountRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        var ingredients = IterableUtils.toList(ingredientRepository.get());
        var types = IngredientType.values();
        for (var type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public Account user(Principal principal) {
        String username = principal.getName();
        Account user = accountRepository.findByUsername(username);
        return user;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing taco: " + taco);

        var savedTaco = tacoRepository.save(taco);
        order.addTaco(savedTaco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
