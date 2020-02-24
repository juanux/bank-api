package com.mybank.co.http;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.mybank.co.Main;
import com.mybank.co.bank.Account;
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
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TransactionEndPointTest {

    private HttpServer server;
    private WebTarget target;
    private static ActorSystem system = ActorSystem.create("bank-test-system");
    private ActorRef eventHandlerActor;
    private ActorRef commandHandlerActor;
    private IAccountService service;


    public TransactionEndPointTest() throws Exception {
    }


    @Before
    public void setUp() throws Exception {
        Connection conn= DatabaseConnection.getConnection();

        IUserDAO userDAO = new UserDAOImpl(conn);
        IAccountDAO accountDAO = new AccountDAOImpl(conn);
        ITransactionDAO transactionDAO = new TransactionDAOImpl(conn);

        IAccountRepository accountRepository = new AccountRepositoryImpl(userDAO,accountDAO);
        ITransactionsRepository transactionLogRepository = new TransactionsRepositoryImpl(transactionDAO);
        service = new AccountServiceImpl(accountRepository);

        eventHandlerActor
                = system.actorOf( TransactionEventsActor.props(transactionLogRepository), "eventHandlerActor-test-1");
        commandHandlerActor
                = system.actorOf( TransactionCommandsActor.props(eventHandlerActor,service), "commandHandlerActor-test-1");

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
     * Test to create an User
     */
    @Test
    public void testTransfer() throws Exception {

        String accountNumber1 = UUID.randomUUID().toString();
        String accountNumber2 = UUID.randomUUID().toString();
        UserDTO dto1 = new UserDTO(UUID.randomUUID().toString(),  "name",  "lastName", "PASSPORT",  "documentId",
                 "email", LocalDate.now(),  "MALE",
                new AccountDTO( accountNumber1, 100D, "EURO"));

        UserDTO dto2 = new UserDTO(UUID.randomUUID().toString(),  "name",  "lastName",
                "PASSPORT",  "documentId",
                "email", LocalDate.now(),  "MALE",
                new AccountDTO( accountNumber2, 100D, "EURO"));

        Account ac1 = service.createAccount(dto1);
        Account ac2 = service.createAccount(dto2);

        TransferDTO transfer = new TransferDTO(ac1.getAccountNumber(),ac2.getAccountNumber(), 50D, "NEW", LocalDateTime.now());
        ConfirmationDTO response = new ConfirmationDTO("OK", "Transaction sucess");
        ConfirmationDTO responseMsg = target.path("transaction").request().post(Entity.json(transfer), ConfirmationDTO.class);

        TransactionListDTO queryResponse = target.path("transaction/" + accountNumber1).request(MediaType.APPLICATION_JSON).get(TransactionListDTO.class);

        System.out.println(queryResponse);
        assertEquals(queryResponse.getTransactions().size(), 1);
        assertEquals(responseMsg.getMessage(), response.getMessage());
        assertEquals(responseMsg.getCode(), response.getCode());
    }


    /**
     * Test to create an Transfer
     */
    @Test
    public void testGetTransactionsByUserId() {
        String userId = "userId";
        ArrayList<TransferDTO> transactions = new ArrayList<TransferDTO>();
        TransactionListDTO response = new TransactionListDTO(transactions);
        TransactionListDTO responseMsg = target.path("transaction/".concat(userId)).request().get(TransactionListDTO.class);

        assertEquals(responseMsg.getTransactions(), response.getTransactions());
    }
}
