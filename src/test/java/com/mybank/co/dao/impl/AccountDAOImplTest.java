package com.mybank.co.dao.impl;

import com.mybank.co.dao.DatabaseConnection;
import com.mybank.co.dao.IAccountDAO;
import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.jooq.tables.records.AccountRecord;
import com.mybank.co.dao.jooq.tables.records.UserRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

public class AccountDAOImplTest {

    private IAccountDAO accountDAO;
    private IUserDAO userDAO;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        try {
            conn = DatabaseConnection.getConnection();
            userDAO = new UserDAOImpl(conn);
            accountDAO = new AccountDAOImpl(conn);

        }catch (Exception e){
            System.out.println("Error connecting to database" );
            throw e;
        }

    }

    @After
    public void tearDown() throws Exception {
        conn.close();

    }


    /**
     * Test to create an account
     */
    @Test
    public void testCreateAccount() {

        String userId = UUID.randomUUID().toString();
        String accountId = UUID.randomUUID().toString();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");
        AccountRecord accountRecord = new AccountRecord(accountId, userId, 1000D, "EUR", true);


        CompletableFuture<Optional<AccountRecord>> optionalCompletableFuture = userDAO.createUser(userRecord)
                .thenCompose(r -> accountDAO.createAccount(accountRecord))
                .thenCompose(r -> accountDAO.getAccountByUserId(userId));

        optionalCompletableFuture.whenComplete((r,ex)->assertEquals( Optional.of(accountRecord),r)).join();

    }


    /**
     * Test to create an account
     */
    @Test
    public void testUpdateBalance() {

        String userId = UUID.randomUUID().toString();
        String accountId = UUID.randomUUID().toString();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");
        AccountRecord accountRecord = new AccountRecord(accountId, userId, 1000D, "EUR", true);


        CompletableFuture<Optional<AccountRecord>> optionalCompletableFuture = userDAO.createUser(userRecord)
                .thenCompose(r -> accountDAO.createAccount(accountRecord))
                .thenCompose(r -> accountDAO.updateBalance(accountRecord.getId(),accountRecord.getBalance() + 2000D))
                .thenCompose(r -> accountDAO.getAccountByUserId(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->
                        assertEquals(r.map(AccountRecord::getBalance).orElseGet(()->0D), 3000D,0D)).join();

    }

    /**
     * Test to delete an account
     */
    @Test
    public void testDeleteAccount() {

        String userId = UUID.randomUUID().toString();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");
        AccountRecord accountRecord = new AccountRecord("01111", userId, 1000D, "EUR", true);


        CompletableFuture<Optional<AccountRecord>> optionalCompletableFuture = userDAO.createUser(userRecord)
                .thenCompose(r -> accountDAO.createAccount(accountRecord))
                .thenCompose(r -> accountDAO.deleteAccount(accountRecord.getId()))
                .thenCompose(r -> userDAO.deleteUser(userId))
                .thenCompose(r -> accountDAO.getAccountByUserId(userId));

        optionalCompletableFuture.whenComplete((r,ex)->assertEquals(r, Optional.empty())).join();

    }




}
