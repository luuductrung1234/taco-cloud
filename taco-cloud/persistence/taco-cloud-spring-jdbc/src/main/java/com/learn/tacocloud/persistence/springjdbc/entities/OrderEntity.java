package com.learn.tacocloud.persistence.springjdbc.entities;

import com.learn.tacocloud.domain.models.Order;
import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Table("ORDERS")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private Long id;
    @Column("DELIVERYNAME")
    private String deliveryName;
    @Column("DELIVERYSTREET")
    private String deliveryStreet;
    @Column("DELIVERYCITY")
    private String deliveryCity;
    @Column("DELIVERYSTATE")
    private String deliveryState;
    @Column("DELIVERYZIP")
    private String deliveryZip;
    @Column("CCNUMBER")
    private String ccNumber;
    @Column("CCEXPIRATION")
    private String ccExpiration;
    @Column("CCCVV")
    private String ccCVV;
    @MappedCollection(idColumn = "ORDERID", keyColumn = "ORDERKEY")
    private List<TacoRef> tacoRefs;
    @Column("CREATEDAT")
    private Date createdAt;

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
        tacoRefs = order.getTacos().stream().map(TacoRef::new).collect(Collectors.toList());
        this.createdAt = new Date();
    }

    public Order toOrder(List<Taco> tacos) {
        return new Order(id, deliveryName, deliveryStreet, deliveryCity, deliveryState, deliveryZip, ccNumber, ccExpiration, ccCVV, tacos, createdAt);
    }
}
