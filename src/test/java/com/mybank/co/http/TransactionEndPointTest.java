package com.mybank.co.http;

import com.mybank.co.Main;
import com.mybank.co.http.dto.ConfirmationDTO;
import com.mybank.co.http.dto.TransactionListDTO;
import com.mybank.co.http.dto.TransferDTO;
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

public class TransactionEndPointTest {

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
     * Test to create an User
     */
    @Test
    public void testTransfer() {
        TransferDTO transfer = new TransferDTO();
        ConfirmationDTO response = new ConfirmationDTO();
        ConfirmationDTO responseMsg = target.path("transaction").request().post(Entity.json(transfer), ConfirmationDTO.class);
        assertEquals(responseMsg, response);
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
