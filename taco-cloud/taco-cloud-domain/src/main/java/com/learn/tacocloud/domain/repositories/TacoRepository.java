package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Taco;

import java.util.Optional;
import java.util.UUID;

public interface TacoRepository {
    Iterable<Taco> get();

    Optional<Taco> get(UUID id);

    Taco save(Taco taco);
}
