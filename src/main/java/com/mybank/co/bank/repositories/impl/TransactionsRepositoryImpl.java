package com.mybank.co.bank.repositories.impl;

import com.mybank.co.bank.ETransactionStatus;
import com.mybank.co.bank.Transfer;
import com.mybank.co.bank.repositories.ITransactionsRepository;
import com.mybank.co.dao.ITransactionDAO;
import com.mybank.co.dao.jooq.tables.records.TransferRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Implementation of Transaction repository
 */
public class TransactionsRepositoryImpl implements ITransactionsRepository {

    ITransactionDAO transactionDAO;

    public TransactionsRepositoryImpl(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public CompletableFuture<List<Transfer>> getTransactionsByAccountId(String accountId) {
       return transactionDAO.getTransactionsById(accountId)
               .thenCompose(r->CompletableFuture.supplyAsync(()-> r.stream()
                       .map(t-> new Transfer(t.getSourceAccountId(), t.getTargetAccountId(),
                               t.getAmount(), LocalDateTime.parse(t.getDateTime()) ,
                               ETransactionStatus.valueOf(t.getStatus()))).collect(Collectors.toList())));

    }

    @Override
    public CompletableFuture<Transfer> saveTransaction(Transfer transaction) {
        TransferRecord record =
                new TransferRecord(UUID.randomUUID().toString(), transaction.getFrom(),
                        transaction.getTo(),transaction.getAmount(), transaction.getDateTime().toString(),
                        transaction.getStatus().toString());
       return transactionDAO.createTransfer(record).thenCompose(r-> CompletableFuture.supplyAsync(()->transaction));

    }
}
