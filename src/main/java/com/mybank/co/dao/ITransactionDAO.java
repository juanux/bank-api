package com.mybank.co.dao;

import com.mybank.co.dao.jooq.tables.records.TransferRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Data Access Operations for Transactions
 */
public interface ITransactionDAO {

    /**
     * Get the transaction list executed for the given account id
     * @param accountId
     * @return
     */
    CompletableFuture<List<TransferRecord>> getTransactionsById(String accountId);

    /**
     * Create a new transfer between 2 accounts
     * @param transfer
     * @return
     */
    CompletableFuture<Integer> createTransfer(TransferRecord transfer);
}
