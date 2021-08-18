package com.learn.tacocloud.persistence.mongodb.repositories;

import com.learn.tacocloud.persistence.mongodb.entities.TacoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongodbTacoRepository extends CrudRepository<TacoEntity, String> {
}
