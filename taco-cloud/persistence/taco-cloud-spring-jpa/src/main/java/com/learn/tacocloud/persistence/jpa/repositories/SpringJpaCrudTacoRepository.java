package com.learn.tacocloud.persistence.jpa.repositories;

import com.learn.tacocloud.persistence.jpa.entities.TacoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringJpaCrudTacoRepository extends CrudRepository<TacoEntity, UUID> {
}
