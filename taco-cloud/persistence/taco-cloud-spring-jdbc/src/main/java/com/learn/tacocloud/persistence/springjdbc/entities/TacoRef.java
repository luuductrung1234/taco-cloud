package com.learn.tacocloud.persistence.springjdbc.entities;

import com.learn.tacocloud.domain.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("ORDER_TACOS")
@AllArgsConstructor
@NoArgsConstructor
public class TacoRef {
    @Id
    private Long id;

    @Column("TACOID")
    private Long tacoId;

    public TacoRef(Taco taco) {
        this.tacoId = taco.getId();
    }
}
