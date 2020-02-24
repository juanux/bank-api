package com.mybank.co.http;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.mybank.co.Main;
import com.mybank.co.bank.actors.TransactionCommandsActor;
import com.mybank.co.bank.actors.TransactionEventsActor;
import com.mybank.co.bank.repositories.IAccountRepository;
import com.mybank.co.bank.repositories.ITransactionsRepository;
import com.mybank.co.bank.repositories.impl.AccountRepositoryImpl;
import com.mybank.co.bank.repositories.impl.TransactionsRepositoryImpl;
import com.mybank.co.bank.service.IAccountService;
import com.mybank.co.bank.service.impl.AccountServiceImpl;
import com.mybank.co.dao.DatabaseConnection;
import com.mybank.co.dao.IAccountDAO;
import com.mybank.co.dao.ITransactionDAO;
import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.impl.AccountDAOImpl;
import com.mybank.co.dao.impl.TransactionDAOImpl;
import com.mybank.co.dao.impl.UserDAOImpl;
import com.mybank.co.http.dto.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.UUID;


import static org.junit.Assert.assertEquals;

public class UserEndpointTest {

    private HttpServer server;
    private WebTarget target;
    private static ActorSystem system = ActorSystem.create("bank-test-system");
    ActorRef eventHandlerActor;
    ActorRef commandHandlerActor;

    public UserEndpointTest() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

        Connection conn= DatabaseConnection.getConnection();

        IUserDAO userDAO = new UserDAOImpl(conn);
        IAccountDAO accountDAO = new AccountDAOImpl(conn);
        ITransactionDAO transactionDAO = new TransactionDAOImpl(conn);

        IAccountRepository accountRepository = new AccountRepositoryImpl(userDAO,accountDAO);
        ITransactionsRepository transactionLogRepository = new TransactionsRepositoryImpl(transactionDAO);
        IAccountService service= new AccountServiceImpl(accountRepository);

        eventHandlerActor
                = system.actorOf( TransactionEventsActor.props(transactionLogRepository), "eventHandlerActor-test-2");
        commandHandlerActor
                = system.actorOf( TransactionCommandsActor.props(eventHandlerActor,service), "commandHandlerActor-test-2");


        server = Main.startServer(service,transactionLogRepository,commandHandlerActor);

        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        system.stop(eventHandlerActor);
        system.stop(commandHandlerActor);
        server.stop();
    }

    /**
     * Test to create an user account
     */
    @Test
    public void testCreateUser() {
        String accountId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        UserDTO user = new UserDTO(userId,"name", "lastName",
                "PASSPORT",  "documentId",  "email",
                 LocalDate.of(1985,12,18),  "MALE",
                new AccountDTO(accountId,  1200D, "EURO"));

        UserDTO responseMsg = target.path("user").request().post(Entity.json(user),UserDTO.class);

        assertEquals(responseMsg, user);
    }

    /**
     * Test to delete an User
     */
    @Test
    public void testDeleteUser() {
        UserDTO user = new UserDTO();
        String id = "id";
        ConfirmationDTO responseMsg = target.path("user/".concat(id)).request().delete(ConfirmationDTO.class);
        assertEquals(responseMsg, new ConfirmationDTO());
    }

    /**
     * Test to get an User By Id
     */
    @Test
    public void testGetUserById() {
        String accountId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        UserDTO user = new UserDTO(userId,"name", "lastName",
                "PASSPORT",  "documentId",  "email",
                LocalDate.of(1985,12,18),  "MALE",
                new AccountDTO(accountId,  1200D, "EURO"));

        UserDTO responseMsg = target.path("user").request().post(Entity.json(user),UserDTO.class);

        assertEquals(responseMsg, user);


        UserDTO queryResult = target.path("user/".concat(userId)).request().get(UserDTO.class);
        assertEquals(queryResult, user);
    }

}
