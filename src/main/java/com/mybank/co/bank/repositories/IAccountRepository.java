package com.mybank.co.bank.repositories;

import com.mybank.co.bank.Account;
import com.mybank.co.bank.ECurrency;
import com.mybank.co.bank.User;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Describe the contract for Account repository
 */
public interface IAccountRepository {
    /**
     * Create a new Account
     * @param user
     * @param accountNumber
     * @param initialBalance
     * @param currency
     * @return
     */
    CompletableFuture<Account> createNewAccount(User user, String accountNumber, Double initialBalance, ECurrency currency);

    /**
     * Get an account by account id
     * @param accountId
     * @return
     */
    CompletableFuture<Optional<Account>> getAccountById(String accountId);

    /**
     * Get an account by user id
     * @param userId
     * @return
     */
    CompletableFuture<Optional<Account>> getAccountByUserId(String userId);

    /**
     * Update a the balance of an specific account
     * @param account
     * @return
     */
    CompletableFuture<Account> updateAccountBalance(Account account);

    /**
     * Delete an account and the related data
     * @param account
     * @return
     */
    CompletableFuture<Account> deleteAccount(Account account);
}

