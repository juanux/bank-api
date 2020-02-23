package com.mybank.co.bank.repositories;

import com.mybank.co.bank.Account;
import com.mybank.co.bank.ECurrency;
import com.mybank.co.bank.User;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IAccountRepository {

    CompletableFuture<Account> createNewAccount(User user, String accountNumber, Double initialBalance, ECurrency currency);
    CompletableFuture<Optional<Account>> getAccountById(String accountId);
    CompletableFuture<Optional<Account>> getAccountByUserId(String userId);
    CompletableFuture<Account> updateAccountBalance(Account account);
    CompletableFuture<Account> deleteAccount(Account account);
}

