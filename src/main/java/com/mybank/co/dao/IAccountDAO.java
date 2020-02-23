package com.mybank.co.dao;


import com.mybank.co.dao.jooq.tables.records.AccountRecord;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IAccountDAO {

    CompletableFuture<Integer> createAccount(AccountRecord accountRecord);
    CompletableFuture<Integer> updateBalance(String id, Double amount);
    CompletableFuture<Integer> deleteAccount(String id);
    CompletableFuture<Optional<AccountRecord>> getAccountByUserId(String userId);
    CompletableFuture<Optional<AccountRecord>> getAccountById(String id);

}
