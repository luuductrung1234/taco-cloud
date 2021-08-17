package com.learn.tacocloud.persistence.cassandra.entities;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.learn.tacocloud.domain.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("ORDERS")
public class OrderEntity {
    @PrimaryKey
    private UUID id;
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
    @Column("CREATEDAT")
    private Date createdAt;

    @Column("TACOS")
    private List<TacoUDT> tacos;

    public OrderEntity(final Order order) {
        this.id = Uuids.timeBased();
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
        return new Order(id, deliveryName, deliveryStreet, deliveryCity, deliveryState, deliveryZip, ccNumber, ccExpiration, ccCVV, tacos.stream().map(TacoUDT::toTaco).collect(Collectors.toList()), createdAt);
    }
}
