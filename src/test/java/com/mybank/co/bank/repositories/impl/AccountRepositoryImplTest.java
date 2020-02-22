package com.mybank.co.bank.repositories.impl;

import com.mybank.co.bank.*;
import com.mybank.co.bank.repositories.IAccountRepository;
import com.mybank.co.dao.IAccountDAO;
import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.impl.AccountDAOImpl;
import com.mybank.co.dao.impl.UserDAOImpl;
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

public class AccountRepositoryImplTest {

    private IAccountRepository accountRepository;
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
            IUserDAO userDAO = new UserDAOImpl(conn);
            IAccountDAO accountDAO = new AccountDAOImpl(conn);

            accountRepository = new AccountRepositoryImpl(userDAO,accountDAO);

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
     * Create an account for an specific user
     */
    @Test
    public void createAccount(){

        User user = new User(UUID.randomUUID(),  "name",  "lastName",
                EDocumentType.PASSPORT,  "documentId", "email",
                LocalDate.now(), EGender.FEMALE);

        String accountNumber = "0001";
        Double initialBalance = 120D;
        ECurrency currency = ECurrency.EURO;
        Account expectedAccount = new Account(accountNumber,user,initialBalance,currency);

        accountRepository.createNewAccount(user,accountNumber,initialBalance,currency)
        .thenCompose(ac -> accountRepository.getAccountById(ac.getAccountNumber()))
                .whenComplete((acc,ex)->assertEquals(acc, Optional.of(expectedAccount)));;

    }

    /**
     * Create an account for an specific user and query the account using the userId
     */
    @Test
    public void getAccountByUserId(){

        UUID userId = UUID.randomUUID();
        User user = new User(UUID.randomUUID(),  "name",  "lastName",
                EDocumentType.PASSPORT,  "documentId", "email",
                LocalDate.now(), EGender.FEMALE);

        String accountNumber = "0001";
        Double initialBalance = 120D;
        ECurrency currency = ECurrency.EURO;
        Account expectedAccount = new Account(accountNumber,user,initialBalance,currency);

        accountRepository.createNewAccount(user,accountNumber,initialBalance,currency)
                .thenCompose(ac -> accountRepository.getAccountByUserId(ac.getUser().getId()))
                .whenComplete((acc,ex)->assertEquals(acc, Optional.of(expectedAccount)));;

    }

    /**
     * Create an account for an specific user update the balance
     */
    @Test
    public void updateAccountBalance(){

        UUID userId = UUID.randomUUID();
        User user = new User(UUID.randomUUID(),  "name",  "lastName",
                EDocumentType.PASSPORT,  "documentId", "email",
                LocalDate.now(), EGender.FEMALE);

        String accountNumber = "0001";
        Double initialBalance = 120D;
        ECurrency currency = ECurrency.EURO;
        Account updatedAccount = new Account(accountNumber,user,initialBalance + 1000D,currency);

        CompletableFuture<Optional<Account>> optionalCompletableFuture = accountRepository.createNewAccount(user, accountNumber, initialBalance, currency)
                .thenCompose(account -> accountRepository.updateAccountBalance(updatedAccount))
                .thenCompose(updated -> accountRepository.getAccountByUserId(updated.getUser().getId()))
                .whenComplete((acc, ex) -> assertEquals(acc, Optional.of(updatedAccount)));
    }


    /**
     * Create an account for an specific user and delete it
     */
    @Test
    public void deleteAccountBalance(){

        UUID userId = UUID.randomUUID();
        User user = new User(UUID.randomUUID(),  "name",  "lastName",
                EDocumentType.PASSPORT,  "documentId", "email",
                LocalDate.now(), EGender.FEMALE);

        String accountNumber = "0001";
        Double initialBalance = 120D;
        ECurrency currency = ECurrency.EURO;

        CompletableFuture<Optional<Account>> optionalCompletableFuture = accountRepository.createNewAccount(user, accountNumber, initialBalance, currency)
                .thenCompose(account -> accountRepository.deleteAccount(account))
                .thenCompose(updated -> accountRepository.getAccountByUserId(updated.getUser().getId()))
                .whenComplete((acc, ex) -> assertEquals(acc, Optional.empty()));
    }

}
