package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Taco;

import java.util.Optional;

public interface TacoRepository {
    Iterable<Taco> get();

    Optional<Taco> get(Long id);

    Taco save(Taco taco);
}
