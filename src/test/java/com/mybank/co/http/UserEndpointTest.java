package com.mybank.co.http;

import com.mybank.co.Main;
import com.mybank.co.http.dto.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import java.time.LocalDate;
import java.util.UUID;


import static org.junit.Assert.assertEquals;

public class UserEndpointTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
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
     * Test to update an User
     */
    @Test
    public void testUpdateUser() {
        UserDTO user = new UserDTO();
        UserDTO responseMsg = target.path("user").request().put(Entity.json(user),UserDTO.class);
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
