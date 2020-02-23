package com.mybank.co;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.mybank.co.bank.actors.TransactionCommandsActor;
import com.mybank.co.bank.actors.TransactionEventsActor;
import com.mybank.co.bank.repositories.IAccountRepository;
import com.mybank.co.bank.repositories.impl.AccountRepositoryImpl;
import com.mybank.co.bank.service.IAccountService;
import com.mybank.co.bank.service.impl.AccountServiceImpl;
import com.mybank.co.dao.DatabaseConnection;
import com.mybank.co.dao.IAccountDAO;
import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.impl.AccountDAOImpl;
import com.mybank.co.dao.impl.UserDAOImpl;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8089/mybank/";

    private static ActorSystem system = ActorSystem.create("test-system");

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(IAccountService service, ActorRef commandHandlerActor) throws Exception {

        // create a resource config that scans for JAX-RS resources and providers
        // in com.mybank.co package
        final ResourceConfig rc = new AppResourceConfig(new AppBinder(service,commandHandlerActor));

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {

        Connection conn= DatabaseConnection.getConnection();

        IUserDAO userDAO = new UserDAOImpl(conn);
        IAccountDAO accountDAO = new AccountDAOImpl(conn);

        IAccountRepository accountRepository = new AccountRepositoryImpl(userDAO,accountDAO);
        IAccountService service= new AccountServiceImpl(accountRepository);

        ActorRef eventHandlerActor
                = system.actorOf( TransactionEventsActor.props(service), "eventHandlerActor");
        ActorRef commandHandlerActor
                = system.actorOf( TransactionCommandsActor.props(eventHandlerActor,service), "commandHandlerActor");

        final HttpServer server = startServer(service,commandHandlerActor);

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

