package com.learn.tacocloud.persistence.springjdbc.entities;

import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("ORDER_TACOS")
@AllArgsConstructor
@NoArgsConstructor
public class TacoRef {
    @Column("TACOID")
    private UUID tacoId;

    public TacoRef(Taco taco) {
        this.tacoId = taco.getId();
    }
}
