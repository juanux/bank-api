package com.mybank.co.dao.impl;

import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.tables.records.UserRecord;
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

    private IUserDAO userDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        Class.forName("org.h2.Driver");

        InputStream stream = getClass().getClassLoader().getResourceAsStream("mybank.properties");
        properties.load(stream);
        String database = properties.getProperty("database.database");
        String url = properties.getProperty("database.url").concat(getClass().getClassLoader().getResource(database).getPath());
        String userName = properties.getProperty("database.user");
        String password = properties.getProperty("database.password");

        try {

            conn =  DriverManager.getConnection(url, userName, password);

            userDao = new UserDAOImpl(conn);

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
        UUID userId = UUID.randomUUID();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");

        CompletableFuture<Optional<UserRecord>> optionalCompletableFuture =
                userDao.createUser(userRecord)
                        .thenCompose(r -> userDao.getUserById(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->assertEquals(r, Optional.of(userRecord)));


    }


    /**
     * Test to update an user
     */
    @Test
    public void testUpdateUser() {
        UUID userId = UUID.randomUUID();
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


        CompletableFuture<Optional<UserRecord>> optionalCompletableFuture = userDao.createUser(userRecord)
                .thenApplyAsync(updated -> userDao.updateUser(userRecordUpdated))
                .thenComposeAsync(r -> userDao.getUserById(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->assertEquals(r, Optional.of(userRecordUpdated)));


    }

    /**
     * Test to delete an user
     */
    @Test
    public void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        UserRecord userRecord = new UserRecord(userId,
                "name",
                "lastName",
                "PASSPORT",
                "documentId",
                "email@email.com",
                LocalDate.now(),
                "MALE");


        CompletableFuture<Optional<UserRecord>> optionalCompletableFuture = userDao.createUser(userRecord)
                .thenApplyAsync(updated -> userDao.deleteUser(userId))
                .thenComposeAsync(r -> userDao.getUserById(userId));

        optionalCompletableFuture
                .whenComplete((r,ex)->assertEquals(r, Optional.empty()));

    }
}
