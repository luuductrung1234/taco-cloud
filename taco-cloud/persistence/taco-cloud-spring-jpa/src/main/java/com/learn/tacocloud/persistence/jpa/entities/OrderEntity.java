package com.learn.tacocloud.persistence.jpa.entities;

import com.learn.tacocloud.domain.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "DELIVERYNAME")
    private String deliveryName;
    @Column(name = "DELIVERYSTREET")
    private String deliveryStreet;
    @Column(name = "DELIVERYCITY")
    private String deliveryCity;
    @Column(name = "DELIVERYSTATE")
    private String deliveryState;
    @Column(name = "DELIVERYZIP")
    private String deliveryZip;
    @Column(name = "CCNUMBER")
    private String ccNumber;
    @Column(name = "CCEXPIRATION")
    private String ccExpiration;
    @Column(name = "CCCVV")
    private String ccCVV;
    @Column(name = "CREATEDAT")
    private Date createdAt;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "ORDER_TACOS",
            joinColumns = @JoinColumn(name = "ORDERID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "TACOID", nullable = false))
    private List<TacoEntity> tacos;

    public OrderEntity(final Order order) {
        this.id = order.getId();
        this.deliveryName = order.getDeliveryName();
        this.deliveryStreet = order.getDeliveryStreet();
        this.deliveryCity = order.getDeliveryCity();
        this.deliveryState = order.getDeliveryState();
        this.deliveryZip = order.getDeliveryZip();
        this.ccNumber = order.getCcNumber();
        this.ccExpiration = order.getCcExpiration();
        this.ccCVV = order.getCcCVV();
        tacos = order.getTacos().stream().map(TacoEntity::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Order toOrder() {
        return new Order(id, deliveryName, deliveryStreet, deliveryCity, deliveryState, deliveryZip, ccNumber, ccExpiration, ccCVV, tacos.stream().map(TacoEntity::toTaco).collect(Collectors.toList()), createdAt);
    }
}
