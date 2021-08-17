package com.learn.tacocloud.web.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DesignTacoAndOrderBrowserTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate rest;

    private static HtmlUnitDriver browser;

    @BeforeAll
    public static void setup() {
        browser = new HtmlUnitDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void closeBrowser() {
        browser.quit();
    }

    @Test
    public void testDesignATacoPage_HappyPath() throws Exception {
        browser.get(homePageUrl());
        clickDesignATaco();
        assertDesignPageElements();
        buildAndSubmitATaco("Basic Taco", "FLTO", "GRBF", "CHED", "TMTO", "SLSA");
        clickBuildAnotherTaco();
        buildAndSubmitATaco("Another Taco", "COTO", "CARN", "JACK", "LETC", "SRCR");
        fillInAndSubmitOrderForm();
        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(homePageUrl());
    }

    @Test
    public void testDesignATacoPage_EmptyOrderInfo() throws Exception {
        browser.get(homePageUrl());
        clickDesignATaco();
        assertDesignPageElements();
        buildAndSubmitATaco("Basic Taco", "FLTO", "GRBF", "CHED", "TMTO", "SLSA");
        submitEmptyOrderForm();
        fillInAndSubmitOrderForm();
        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(homePageUrl());
    }

    @Test
    public void testDesignATacoPage_InvalidOrderInfo() throws Exception {
        browser.get(homePageUrl());
        clickDesignATaco();
        assertDesignPageElements();
        buildAndSubmitATaco("Basic Taco", "FLTO", "GRBF", "CHED", "TMTO", "SLSA");
        submitInvalidOrderForm();
        fillInAndSubmitOrderForm();
        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(homePageUrl());
    }

    // ======================================
    // Browser test action methods
    // ======================================

    private void buildAndSubmitATaco(String name, String... ingredients) {
        assertDesignPageElements();

        for (String ingredient : ingredients) {
            browser.findElementByCssSelector("input[value='" + ingredient + "']").click();
        }
        browser.findElementByCssSelector("input#name").sendKeys(name);
        browser.findElementByCssSelector("form").submit();
    }

    private void assertDesignPageElements() {
        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(designPageUrl());
        List<WebElement> ingredientGroups = browser.findElementsByClassName("ingredient-group");
        Assertions.assertThat(ingredientGroups.size()).isEqualTo(5);

        WebElement wrapGroup = browser.findElementByCssSelector("div.ingredient-group#wraps");
        List<WebElement> wraps = wrapGroup.findElements(By.tagName("div"));
        Assertions.assertThat(wraps.size()).isEqualTo(2);
        assertIngredient(wrapGroup, 0, new ArrayList<>() {{
                    add("FLTO");
                    add("COTO");
                }},
                new ArrayList<>() {{
                    add("Flour Tortilla");
                    add("Corn Tortilla");
                }});
        assertIngredient(wrapGroup, 1, new ArrayList<>() {{
                    add("FLTO");
                    add("COTO");
                }},
                new ArrayList<>() {{
                    add("Flour Tortilla");
                    add("Corn Tortilla");
                }});

        WebElement proteinGroup = browser.findElementByCssSelector("div.ingredient-group#proteins");
        List<WebElement> proteins = proteinGroup.findElements(By.tagName("div"));
        Assertions.assertThat(proteins.size()).isEqualTo(2);
        assertIngredient(proteinGroup, 0, new ArrayList<>() {{
                    add("GRBF");
                    add("CARN");
                }},
                new ArrayList<>() {{
                    add("Ground Beef");
                    add("Carnitas");
                }});
        assertIngredient(proteinGroup, 1, new ArrayList<>() {{
                    add("GRBF");
                    add("CARN");
                }},
                new ArrayList<>() {{
                    add("Ground Beef");
                    add("Carnitas");
                }});

        WebElement cheeseGroup = browser.findElementByCssSelector("div.ingredient-group#cheeses");
        List<WebElement> cheeses = proteinGroup.findElements(By.tagName("div"));
        Assertions.assertThat(cheeses.size()).isEqualTo(2);
        assertIngredient(cheeseGroup, 0, new ArrayList<>() {{
                    add("CHED");
                    add("JACK");
                }},
                new ArrayList<String>() {{
                    add("Cheddar");
                    add("Monterrey Jack");
                }});
        assertIngredient(cheeseGroup, 1, new ArrayList<>() {{
                    add("CHED");
                    add("JACK");
                }},
                new ArrayList<String>() {{
                    add("Cheddar");
                    add("Monterrey Jack");
                }});

        WebElement veggieGroup = browser.findElementByCssSelector("div.ingredient-group#veggies");
        List<WebElement> veggies = proteinGroup.findElements(By.tagName("div"));
        Assertions.assertThat(veggies.size()).isEqualTo(2);
        assertIngredient(veggieGroup, 0, new ArrayList<>() {{
                    add("TMTO");
                    add("LETC");
                }},
                new ArrayList<>() {{
                    add("Diced Tomatoes");
                    add("Lettuce");
                }});
        assertIngredient(veggieGroup, 1, new ArrayList<>() {{
                    add("TMTO");
                    add("LETC");
                }},
                new ArrayList<>() {{
                    add("Diced Tomatoes");
                    add("Lettuce");
                }});

        WebElement sauceGroup = browser.findElementByCssSelector("div.ingredient-group#sauces");
        List<WebElement> sauces = proteinGroup.findElements(By.tagName("div"));
        Assertions.assertThat(sauces.size()).isEqualTo(2);
        assertIngredient(sauceGroup, 0, new ArrayList<>() {{
                    add("SLSA");
                    add("SRCR");
                }},
                new ArrayList<>() {{
                    add("Salsa");
                    add("Sour Cream");
                }});
        assertIngredient(sauceGroup, 1, new ArrayList<>() {{
                    add("SLSA");
                    add("SRCR");
                }},
                new ArrayList<>() {{
                    add("Salsa");
                    add("Sour Cream");
                }});
    }

    private void fillInAndSubmitOrderForm() {
        Assertions.assertThat(browser.getCurrentUrl()).startsWith(orderDetailsPageUrl());
        fillField("input#deliveryName", "Ima Hungry");
        fillField("input#deliveryStreet", "1234 Culinary Blvd.");
        fillField("input#deliveryCity", "Foodsville");
        fillField("input#deliveryState", "CO");
        fillField("input#deliveryZip", "81019");
        fillField("input#ccNumber", "4111111111111111");
        fillField("input#ccExpiration", "10/19");
        fillField("input#ccCVV", "123");
        browser.findElementByCssSelector("form").submit();
    }

    private void submitEmptyOrderForm() {
        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(currentOrderDetailsPageUrl());
        browser.findElementByCssSelector("form").submit();

        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(orderDetailsPageUrl());

        List<String> validationErrors = getValidationErrorTexts();
        Assertions.assertThat(validationErrors.size()).isEqualTo(9);
        Assertions.assertThat(validationErrors).containsExactlyInAnyOrder(
                "Please correct the problems below and resubmit.",
                "Delivery name is required",
                "Street is required",
                "City is required",
                "State is required",
                "Zip code is required",
                "Not a valid credit card number",
                "Must be formatted MM/YY",
                "Invalid CVV"
        );
    }

    private List<String> getValidationErrorTexts() {
        List<WebElement> validationErrorElements = browser.findElementsByClassName("validationError");
        List<String> validationErrors = validationErrorElements.stream()
                .map(el -> el.getText())
                .collect(Collectors.toList());
        return validationErrors;
    }

    private void submitInvalidOrderForm() {
        Assertions.assertThat(browser.getCurrentUrl()).startsWith(orderDetailsPageUrl());
        fillField("input#deliveryName", "I");
        fillField("input#deliveryStreet", "1");
        fillField("input#deliveryCity", "F");
        fillField("input#deliveryState", "C");
        fillField("input#deliveryZip", "8");
        fillField("input#ccNumber", "1234432112344322");
        fillField("input#ccExpiration", "14/91");
        fillField("input#ccCVV", "1234");
        browser.findElementByCssSelector("form").submit();

        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(orderDetailsPageUrl());

        List<String> validationErrors = getValidationErrorTexts();
        Assertions.assertThat(validationErrors.size()).isEqualTo(4);
        Assertions.assertThat(validationErrors).containsExactlyInAnyOrder(
                "Please correct the problems below and resubmit.",
                "Not a valid credit card number",
                "Must be formatted MM/YY",
                "Invalid CVV"
        );
    }

    private void fillField(String fieldName, String value) {
        WebElement field = browser.findElementByCssSelector(fieldName);
        field.clear();
        field.sendKeys(value);
    }

    private void assertIngredient(WebElement ingredientGroup,
                                  int ingredientIdx, List<String> ids, List<String> names) {
        List<WebElement> proteins = ingredientGroup.findElements(By.tagName("div"));
        WebElement ingredient = proteins.get(ingredientIdx);
        Assertions.assertThat(ids).contains(ingredient.findElement(By.tagName("input")).getAttribute("value"));
        Assertions.assertThat(names).contains(ingredient.findElement(By.tagName("span")).getText());
    }

    private void assertIngredient(WebElement ingredientGroup,
                                  int ingredientIdx, String id, String name) {
        List<WebElement> proteins = ingredientGroup.findElements(By.tagName("div"));
        WebElement ingredient = proteins.get(ingredientIdx);
        Assertions.assertThat(
                ingredient.findElement(By.tagName("input")).getAttribute("value"))
                .isEqualTo(id);
        Assertions.assertThat(
                ingredient.findElement(By.tagName("span")).getText())
                .isEqualTo(name);
    }

    private void clickDesignATaco() {
        Assertions.assertThat(browser.getCurrentUrl()).isEqualTo(homePageUrl());
        browser.findElementByCssSelector("a[id='design']").click();
    }

    private void clickBuildAnotherTaco() {
        Assertions.assertThat(browser.getCurrentUrl()).startsWith(orderDetailsPageUrl());
        browser.findElementByCssSelector("a[id='another']").click();
    }

    private String designPageUrl() {
        return homePageUrl() + "design";
    }

    private String homePageUrl() {
        return "http://localhost:" + port + "/";
    }

    private String orderDetailsPageUrl() {
        return homePageUrl() + "orders";
    }

    private String currentOrderDetailsPageUrl() {
        return homePageUrl() + "orders/current";
    }
}
