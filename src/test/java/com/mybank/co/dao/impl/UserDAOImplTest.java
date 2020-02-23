package com.mybank.co.dao.impl;

import com.mybank.co.dao.DatabaseConnection;
import com.mybank.co.dao.IUserDAO;
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

public class UserDAOImplTest {

    private IUserDAO userDAO;
    private Connection conn;

    @Before
    public void setUp() throws Exception {

        try {

            conn =  DatabaseConnection.getConnection();
            userDAO = new UserDAOImpl(conn);

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
     * Test to create an user
     */
    @Test
    public void testCreateUser() {
        String userId = UUID.randomUUID().toString();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");

        CompletableFuture<Optional<UserRecord>> optionalCompletableFuture =
                userDAO.createUser(userRecord)
                        .thenCompose(r -> userDAO.getUserById(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->assertEquals(r, Optional.of(userRecord)));


    }


    /**
     * Test to update an user
     */
    @Test
    public void testUpdateUser() {
        String userId = UUID.randomUUID().toString();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");

        UserRecord userRecordUpdated = new UserRecord(userId,
                "nameUpdated",
                "lastNameUpdated",
                "DNI",
                "documentIduPDATED",
                "emailupdated@email.com",
                LocalDate.now(),
                "FEMALE");


        CompletableFuture<Optional<UserRecord>> optionalCompletableFuture = userDAO.createUser(userRecord)
                .thenApplyAsync(updated -> userDAO.updateUser(userRecordUpdated))
                .thenComposeAsync(r -> userDAO.getUserById(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->assertEquals(r, Optional.of(userRecordUpdated)));


    }

    /**
     * Test to delete an user
     */
    @Test
    public void testDeleteUser() {
        String userId = UUID.randomUUID().toString();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");


        CompletableFuture<Optional<UserRecord>> optionalCompletableFuture = userDAO.createUser(userRecord)
                .thenApplyAsync(updated -> userDAO.deleteUser(userId))
                .thenComposeAsync(r -> userDAO.getUserById(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->assertEquals(r, Optional.empty()));

    }
}
