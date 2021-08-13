package com.learn.tacocloud.persistence.springjdbc.repositories;

import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import com.learn.tacocloud.persistence.springjdbc.entities.TacoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CrudTacoRepository implements TacoRepository {
    private final SpringJdbcCrudTacoRepository helperRepository;

    public CrudTacoRepository(SpringJdbcCrudTacoRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Taco save(Taco taco) {
        var savedTaco = helperRepository.save(new TacoEntity(taco));
        taco.setId(savedTaco.getId());
        return taco;
    }
}
