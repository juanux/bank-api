package com.mybank.co.dao.impl;

import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.Tables;
import com.mybank.co.dao.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserDAOImpl implements IUserDAO {

    private Connection connection;
    private DSLContext dsl;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        this.dsl = DSL.using(connection, SQLDialect.H2);
    }

    @Override
    public CompletableFuture<Integer> createUser(UserRecord userRecord) {
       return CompletableFuture.supplyAsync(() -> dsl.insertInto(Tables.USER,
                Tables.USER.ID,
                Tables.USER.NAME,
                Tables.USER.LAST_NAME,
                Tables.USER.DOCUMENT_TYPE,
                Tables.USER.DOCUMENT_ID,
                Tables.USER.BIRTH_DAY,
                Tables.USER.EMAIL,
                Tables.USER.GENDER).values(
                userRecord.getId(),
                userRecord.getName(),
                userRecord.getLastName(),
                userRecord.getDocumentType(),
                userRecord.getDocumentId(),
                userRecord.getBirthDay(),
                userRecord.getEmail(),
                userRecord.getGender()
        ).execute());

    }

    @Override
    public CompletableFuture<Integer> updateUser(UserRecord userRecord) {

        return CompletableFuture.supplyAsync(() ->dsl.update(Tables.USER)
                .set(Tables.USER.NAME,userRecord.getName())
                .set(Tables.USER.LAST_NAME,userRecord.getLastName())
                .set(Tables.USER.DOCUMENT_TYPE,userRecord.getDocumentType())
                .set(Tables.USER.DOCUMENT_ID,userRecord.getDocumentId())
                .set(Tables.USER.BIRTH_DAY,userRecord.getBirthDay())
                .set(Tables.USER.EMAIL,userRecord.getEmail())
                .set(Tables.USER.GENDER,userRecord.getGender())
                 .where(Tables.USER.ID.eq(userRecord.getId())).execute());

    }

    @Override
    public CompletableFuture<Integer> deleteUser(UUID userId) {
        return CompletableFuture.supplyAsync(() -> dsl.delete(Tables.USER).where(Tables.USER.ID.eq(userId)).execute());

    }

    @Override
    public CompletableFuture<Optional<UserRecord>> getUserById(UUID userId) {

        return CompletableFuture.supplyAsync(() -> dsl.select().from(Tables.USER).where(Tables.USER.ID.eq(userId)).fetchOptional()
                .map(r -> new UserRecord(
                        r.getValue(Tables.USER.ID),
                        r.getValue(Tables.USER.NAME),
                        r.getValue(Tables.USER.LAST_NAME),
                        r.getValue(Tables.USER.DOCUMENT_TYPE),
                        r.getValue(Tables.USER.DOCUMENT_ID),
                        r.getValue(Tables.USER.EMAIL),
                        r.getValue(Tables.USER.BIRTH_DAY),
                        r.getValue(Tables.USER.GENDER))));
    }

}
