package com.mybank.co.dao.impl;

import com.mybank.co.dao.ITransactionDAO;
import com.mybank.co.dao.jooq.Tables;
import com.mybank.co.dao.jooq.tables.records.TransferRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of data access operations for transactions
 */
public class TransactionDAOImpl implements ITransactionDAO {

    private Connection connection;
    private DSLContext dsl;

    public TransactionDAOImpl(Connection connection) {
        this.connection = connection;
        this.dsl = DSL.using(this.connection, SQLDialect.SQLITE);
    }

    @Override
    public CompletableFuture<List<TransferRecord>> getTransactionsById(String accountId) {

        return CompletableFuture
                .supplyAsync(()->
                        dsl.select().from(Tables.TRANSFER)
                                .where(Tables.TRANSFER.SOURCE_ACCOUNT_ID.eq(accountId))
                                .or(Tables.TRANSFER.TARGET_ACCOUNT_ID.eq(accountId)).orderBy(Tables.TRANSFER.DATE_TIME).fetch()
                                .map(r-> {
                                    return  new TransferRecord(r.getValue(Tables.TRANSFER.ID),
                                        r.getValue(Tables.TRANSFER.SOURCE_ACCOUNT_ID),
                                        r.getValue(Tables.TRANSFER.TARGET_ACCOUNT_ID),
                                        r.getValue(Tables.TRANSFER.AMOUNT),
                                        r.getValue(Tables.TRANSFER.DATE_TIME),
                                        r.getValue(Tables.TRANSFER.STATUS));
                                }

                ));

    }

    @Override
    public CompletableFuture<Integer> createTransfer(TransferRecord transfer) {
        return CompletableFuture.supplyAsync(()-> dsl.insertInto(Tables.TRANSFER,
                Tables.TRANSFER.ID,
                Tables.TRANSFER.SOURCE_ACCOUNT_ID,
                Tables.TRANSFER.TARGET_ACCOUNT_ID,
                Tables.TRANSFER.AMOUNT,
                Tables.TRANSFER.DATE_TIME,
                Tables.TRANSFER.STATUS)
                .values(transfer.getId(),
                        transfer.getSourceAccountId(),
                        transfer.getTargetAccountId(),
                        transfer.getAmount(),
                        transfer.getDateTime(),
                        transfer.getStatus()).execute());
    }

}
