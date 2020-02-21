package com.mybank.co.http;

import com.mybank.co.Main;
import com.mybank.co.http.dto.ConfirmationDTO;
import com.mybank.co.http.dto.TransactionListDTO;
import com.mybank.co.http.dto.TransferDTO;
import com.mybank.co.http.dto.UserDTO;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import java.util.ArrayList;

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
     * Test to create an Transfer
     */
    @Test
    public void testCreateUser() {
        UserDTO user = new UserDTO();
        UserDTO responseMsg = target.path("transaction").request().post(Entity.json(user),UserDTO.class);
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
        UserDTO user = new UserDTO();
        String id = "id";
        UserDTO responseMsg = target.path("user/".concat(id)).request().get(UserDTO.class);
        assertEquals(responseMsg, user);
    }

}
