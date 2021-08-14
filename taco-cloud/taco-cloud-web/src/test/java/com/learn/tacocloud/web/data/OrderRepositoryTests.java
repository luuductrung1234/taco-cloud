package com.learn.tacocloud.web.data;

import com.learn.tacocloud.domain.enums.IngredientType;
import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.OrderRepository;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderRepositoryTests {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TacoRepository tacoRepository;

    @Test
    public void saveOrderWithTwoTacos() {
        var order = new Order();
        order.setDeliveryName("Test McTest");
        order.setDeliveryStreet("1234 Test Lane");
        order.setDeliveryCity("Testville");
        order.setDeliveryState("CO");
        order.setDeliveryZip("80123");
        order.setCcNumber("4111111111111111");
        order.setCcExpiration("10/23");
        order.setCcCVV("123");

        var taco1 = new Taco();
        taco1.setName("Taco One");
        taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
        taco1.addIngredient(new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
        taco1.addIngredient(new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
        var savedTaco1 = tacoRepository.save(taco1);
        order.addTaco(savedTaco1);

        var taco2 = new Taco();
        taco2.setName("Taco Two");
        taco2.addIngredient(new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP));
        taco2.addIngredient(new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
        taco2.addIngredient(new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
        var savedTaco2 = tacoRepository.save(taco2);
        order.addTaco(savedTaco2);

        var savedOrder = orderRepository.save(order);
        Assertions.assertThat(savedOrder.getId()).isNotNull();

        var fetchedOrder = orderRepository.get(savedOrder.getId()).get();
        Assertions.assertThat(fetchedOrder.getDeliveryName()).isEqualTo("Test McTest");
        Assertions.assertThat(fetchedOrder.getDeliveryStreet()).isEqualTo("1234 Test Lane");
        Assertions.assertThat(fetchedOrder.getDeliveryCity()).isEqualTo("Testville");
        Assertions.assertThat(fetchedOrder.getDeliveryState()).isEqualTo("CO");
        Assertions.assertThat(fetchedOrder.getDeliveryZip()).isEqualTo("80123");
        Assertions.assertThat(fetchedOrder.getCcNumber()).isEqualTo("4111111111111111");
        Assertions.assertThat(fetchedOrder.getCcExpiration()).isEqualTo("10/23");
        Assertions.assertThat(fetchedOrder.getCcCVV()).isEqualTo("123");
        Assertions.assertThat(fetchedOrder.getCreatedAt().getTime()).isEqualTo(savedOrder.getCreatedAt().getTime());

        List<Taco> tacos = fetchedOrder.getTacos();
        Assertions.assertThat(tacos.size()).isEqualTo(2);
        Assertions.assertThat(tacos).containsExactlyInAnyOrder(taco1, taco2);
    }
}




