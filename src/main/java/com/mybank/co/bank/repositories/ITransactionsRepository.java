package com.mybank.co.bank.repositories;

import com.mybank.co.bank.Transfer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransactionsRepository {
    /**
     * Get all the transaction where a specific account id in involved
     * @param accountId
     * @return
     */
    CompletableFuture<List<Transfer>> getTransactionsByAccountId(String accountId);

    /**
     * Create a log with the details of an specific transaction
     * @param transaction
     * @return
     */
    CompletableFuture<Transfer> saveTransaction(Transfer transaction);
}
