package com.learn.tacocloud.persistence.jpa.repositories;

import com.learn.tacocloud.persistence.jpa.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringJpaCrudAccountRepository extends CrudRepository<AccountEntity, UUID> {
    public AccountEntity findByUsername(String username);
}
