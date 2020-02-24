package com.mybank.co.dao;

import com.mybank.co.dao.jooq.tables.records.TransferRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransactionDAO {

    CompletableFuture<List<TransferRecord>> getTransactionsById(String accountId);
    CompletableFuture<Integer> createTransfer(TransferRecord transfer);
}
