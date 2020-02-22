package com.mybank.co.dao.impl;

import com.mybank.co.dao.IAccountDAO;
import com.mybank.co.dao.Tables;
import com.mybank.co.dao.tables.records.AccountRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AccountDAOImpl implements IAccountDAO {

    private Connection connection;
    private DSLContext dsl;

    public AccountDAOImpl(Connection connection) {
        this.connection = connection;
        this.dsl = DSL.using(this.connection, SQLDialect.H2);
    }

    @Override
    public CompletableFuture<Integer> createAccount(AccountRecord accountRecord) {

        return CompletableFuture.supplyAsync(()-> dsl.insertInto(Tables.ACCOUNT,
                Tables.ACCOUNT.ID,
                Tables.ACCOUNT.USER_ID,
                Tables.ACCOUNT.BALANCE,
                Tables.ACCOUNT.CURRENCY,
                Tables.ACCOUNT.ACTIVE)
                .values(accountRecord.getId(),
                        accountRecord.getUserId(),
                        accountRecord.getBalance(),
                        accountRecord.getCurrency(),
                        accountRecord.getActive()).execute());


    }

    @Override
    public CompletableFuture<Integer> updateBalance(String id, Double amount) {
        return CompletableFuture.supplyAsync(()->dsl.update(Tables.ACCOUNT).set(Tables.ACCOUNT.BALANCE,amount)
                .where(Tables.ACCOUNT.ID.eq(id)).execute());
    }

    @Override
    public CompletableFuture<Integer> deleteAccount(String id) {
        return CompletableFuture.supplyAsync(()->dsl.delete(Tables.ACCOUNT).where(Tables.ACCOUNT.ID.eq(id)).execute());
    }

    @Override
    public CompletableFuture<Optional<AccountRecord>> getAccountByUserId(UUID userId) {

        return CompletableFuture.supplyAsync(()->
                dsl.select().from(Tables.ACCOUNT)
                        .where(Tables.ACCOUNT.USER_ID.eq(userId)).fetchOptional()
                .map(acc-> new AccountRecord(acc.getValue(Tables.ACCOUNT.ID),
                                acc.getValue(Tables.ACCOUNT.USER_ID),
                                acc.getValue(Tables.ACCOUNT.BALANCE),
                                acc.getValue(Tables.ACCOUNT.CURRENCY),
                                acc.getValue(Tables.ACCOUNT.ACTIVE))));
    }

    @Override
    public CompletableFuture<Optional<AccountRecord>> getAccountById(String id) {
        return CompletableFuture.supplyAsync(()->
                dsl.select().from(Tables.ACCOUNT)
                        .where(Tables.ACCOUNT.ID.eq(id)).fetchOptional()
                        .map(acc-> new AccountRecord(acc.getValue(Tables.ACCOUNT.ID),
                                acc.getValue(Tables.ACCOUNT.USER_ID),
                                acc.getValue(Tables.ACCOUNT.BALANCE),
                                acc.getValue(Tables.ACCOUNT.CURRENCY),
                                acc.getValue(Tables.ACCOUNT.ACTIVE))));
    }
}
