package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
