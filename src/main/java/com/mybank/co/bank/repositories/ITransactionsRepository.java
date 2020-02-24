package com.mybank.co.bank.repositories;

import com.mybank.co.bank.Transfer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransactionsRepository {
    CompletableFuture<List<Transfer>> getTransactionsByAccountId(String accountId);
    CompletableFuture<Transfer> saveTransaction(Transfer transaction);
}
