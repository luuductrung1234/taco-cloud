package com.learn.tacocloud.persistence.springjdbc.repositories;

import com.learn.tacocloud.domain.models.Taco;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import com.learn.tacocloud.domain.repositories.TacoRepository;
import com.learn.tacocloud.persistence.springjdbc.entities.TacoEntity;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CrudTacoRepository implements TacoRepository {
    private final SpringJdbcCrudTacoRepository helperRepository;
    private final IngredientRepository ingredientRepository;

    public CrudTacoRepository(SpringJdbcCrudTacoRepository helperRepository, IngredientRepository ingredientRepository) {
        this.helperRepository = helperRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Iterable<Taco> get() {
        var tacoEntities = IterableUtils.toList(helperRepository.findAll());
        return attachIngredient(tacoEntities);
    }

    @Override
    public Optional<Taco> get(UUID id) {
        var tacoEntity = helperRepository.findById(id);
        if (tacoEntity.isPresent()) {
            var taco = attachIngredient(new ArrayList<>() {{
                add(tacoEntity.get());
            }}).stream().findFirst();
            return taco;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Taco save(Taco taco) {
        var savedTaco = helperRepository.save(new TacoEntity(taco));
        taco.setId(savedTaco.getId());
        taco.setCreatedAt(savedTaco.getCreatedAt());
        return taco;
    }

    private List<Taco> attachIngredient(List<TacoEntity> tacoEntities) {
        var ingredients = IterableUtils.toList(ingredientRepository.get());
        var result = new ArrayList<Taco>();
        for (var tacoEntity : tacoEntities) {
            var ingredientIds = tacoEntity.getIngredientRefs().stream().map(i -> i.getIngredientId()).collect(Collectors.toList());
            var attachedIngredients = ingredients.stream().filter(i -> ingredientIds.contains(i.getId())).collect(Collectors.toList());
            var taco = tacoEntity.toTaco(attachedIngredients);
            result.add(taco);
        }
        return result;
    }
}
