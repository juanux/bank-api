package com.mybank.co.http;

import com.mybank.co.http.dto.ConfirmationDTO;
import com.mybank.co.http.dto.DTO;
import com.mybank.co.http.dto.TransactionListDTO;
import com.mybank.co.http.dto.TransferDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("transaction")
public class TransactionEndpoint {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO createTransfer(TransferDTO transferDTO){


        return new ConfirmationDTO();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionListDTO getTransactionsByUserId(@PathParam("userId") String id){
        return new TransactionListDTO(new ArrayList<>());

    }

}
