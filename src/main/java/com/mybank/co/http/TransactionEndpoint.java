package com.mybank.co.http;

import akka.actor.ActorRef;
import akka.util.Timeout;
import com.mybank.co.bank.actors.commands.ExecuteTransferCmd;
import com.mybank.co.http.dto.ConfirmationDTO;
import com.mybank.co.http.dto.DTO;
import com.mybank.co.http.dto.TransactionListDTO;
import com.mybank.co.http.dto.TransferDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.ArrayList;

import static akka.pattern.Patterns.ask;


@Path("transaction")
public class TransactionEndpoint {

    @Inject
    ActorRef commandHandler;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO createTransfer(TransferDTO transferDTO){

        ExecuteTransferCmd command = new ExecuteTransferCmd(transferDTO.getOriginAccountId(),
                transferDTO.getTargetAccountId(), transferDTO.getAmount());

        try {
            Object result =
                    ask(commandHandler, command, Duration.ofMillis(1000)).toCompletableFuture().join();
        }catch(Exception e){
            System.out.println("Error ===" + e.getMessage());
        }

        return new ConfirmationDTO();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionListDTO getTransactionsByUserId(@PathParam("userId") String id){
        return new TransactionListDTO(new ArrayList<>());

    }

}
