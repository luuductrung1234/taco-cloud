package com.learn.tacocloud.persistence.cassandra.repositories;

import com.learn.tacocloud.domain.models.Ingredient;
import com.learn.tacocloud.domain.repositories.IngredientRepository;
import com.learn.tacocloud.persistence.cassandra.entities.IngredientEntity;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CrudIngredientRepository implements IngredientRepository {
    private final SpringCassandraIngredientRepository helperRepository;

    public CrudIngredientRepository(SpringCassandraIngredientRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Iterable<Ingredient> get() {
        return IterableUtils.toList(helperRepository.findAll())
                .stream().map(IngredientEntity::toIngredient).collect(Collectors.toList());
    }

    @Override
    public Optional<Ingredient> get(String id) {
        var ingredientEntity = helperRepository.findById(id);
        if (ingredientEntity.isPresent()) {
            return Optional.of(ingredientEntity.get().toIngredient());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return helperRepository.save(new IngredientEntity(ingredient)).toIngredient();
    }
}
