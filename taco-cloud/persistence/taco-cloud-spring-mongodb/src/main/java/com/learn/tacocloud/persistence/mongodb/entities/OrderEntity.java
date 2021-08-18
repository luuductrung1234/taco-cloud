package com.learn.tacocloud.persistence.mongodb.entities;

import com.learn.tacocloud.domain.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderEntity {
    @Id
    private String id;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private Date createdAt;
    private List<TacoUDT> tacos;

    public OrderEntity(final Order order) {
        this.id = UUID.randomUUID().toString();
        this.deliveryName = order.getDeliveryName();
        this.deliveryStreet = order.getDeliveryStreet();
        this.deliveryCity = order.getDeliveryCity();
        this.deliveryState = order.getDeliveryState();
        this.deliveryZip = order.getDeliveryZip();
        this.ccNumber = order.getCcNumber();
        this.ccExpiration = order.getCcExpiration();
        this.ccCVV = order.getCcCVV();
        tacos = order.getTacos().stream().map(TacoUDT::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Order toOrder() {
        return new Order(UUID.fromString(id), deliveryName, deliveryStreet, deliveryCity, deliveryState, deliveryZip, ccNumber, ccExpiration, ccCVV, tacos.stream().map(TacoUDT::toTaco).collect(Collectors.toList()), createdAt);
    }
}
