package com.learn.tacocloud.persistence.jpa.repositories;

import com.learn.tacocloud.domain.models.Account;
import com.learn.tacocloud.domain.repositories.AccountRepository;
import com.learn.tacocloud.persistence.jpa.entities.AccountEntity;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class CrudAccountRepository implements AccountRepository {
    private final SpringJpaCrudAccountRepository helperRepository;

    public CrudAccountRepository(SpringJpaCrudAccountRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Iterable<Account> findAll() {
        return IterableUtils.toList(helperRepository.findAll())
                .stream().map(AccountEntity::toAccount).collect(Collectors.toList());
    }

    @Override
    public Account findByUsername(String username) {
        var account = helperRepository.findByUsername(username);
        return account.toAccount();
    }

    @Override
    public Account save(Account account) {
        return helperRepository.save(new AccountEntity(account)).toAccount();
    }
}
