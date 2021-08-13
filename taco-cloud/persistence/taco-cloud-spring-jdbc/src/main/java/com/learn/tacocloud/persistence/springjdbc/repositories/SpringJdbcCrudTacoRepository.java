package com.learn.tacocloud.persistence.springjdbc.repositories;

import com.learn.tacocloud.persistence.springjdbc.entities.TacoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJdbcCrudTacoRepository extends CrudRepository<TacoEntity, Long> {
}
