package com.learn.tacocloud.domain.repositories;

import com.learn.tacocloud.domain.models.Account;

public interface AccountRepository {
    Iterable<Account> findAll();
    Account findByUsername(String username);
    Account save(Account account);
}
