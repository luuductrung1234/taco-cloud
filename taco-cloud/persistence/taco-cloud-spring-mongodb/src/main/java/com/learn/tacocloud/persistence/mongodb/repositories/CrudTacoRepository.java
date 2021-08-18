package com.learn.tacocloud.persistence.mongodb.repositories;

import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import com.learn.tacocloud.persistence.mongodb.entities.TacoEntity;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CrudTacoRepository implements TacoRepository {
    private final SpringMongodbTacoRepository helperRepository;

    public CrudTacoRepository(SpringMongodbTacoRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Iterable<Taco> get() {
        return IterableUtils.toList(helperRepository.findAll())
                .stream().map(TacoEntity::toTaco).collect(Collectors.toList());
    }

    @Override
    public Optional<Taco> get(UUID id) {
        var tacoEntity = helperRepository.findById(id.toString());
        if (tacoEntity.isPresent()) {
            return Optional.of(tacoEntity.get().toTaco());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Taco save(Taco taco) {
        return helperRepository.save(new TacoEntity(taco)).toTaco();
    }
}
