package com.mybank.co.dao;


import com.mybank.co.dao.jooq.tables.records.AccountRecord;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Data Access Operations for Account
 */
public interface IAccountDAO {

    /**
     * Create a new Account
     * @param accountRecord
     * @return
     */
    CompletableFuture<Integer> createAccount(AccountRecord accountRecord);

    /**
     * Update the balance of a given Account
     * @param id
     * @param amount
     * @return
     */
    CompletableFuture<Integer> updateBalance(String id, Double amount);

    /**
     * Delete an account
     * @param id
     * @return
     */
    CompletableFuture<Integer> deleteAccount(String id);

    /**
     * Get an account for the given user id
     * @param userId
     * @return
     */
    CompletableFuture<Optional<AccountRecord>> getAccountByUserId(String userId);

    /**
     * Get an account for a given account id
     * @param id
     * @return
     */
    CompletableFuture<Optional<AccountRecord>> getAccountById(String id);

}
