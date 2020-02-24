package com.mybank.co.http;

import akka.actor.ActorRef;
import com.mybank.co.bank.Transfer;
import com.mybank.co.bank.actors.commands.ExecuteTransferCmd;
import com.mybank.co.bank.actors.notifications.TransferErrorNtf;
import com.mybank.co.bank.repositories.ITransactionsRepository;
import com.mybank.co.http.dto.ConfirmationDTO;
import com.mybank.co.http.dto.DTO;
import com.mybank.co.http.dto.TransactionListDTO;
import com.mybank.co.http.dto.TransferDTO;
import com.mybank.co.http.dto.error.TransferError;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static akka.pattern.Patterns.ask;


@Path("transaction")
public class TransactionEndpoint {

    @Inject
    ActorRef commandHandler;
    @Inject
    ITransactionsRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO createTransfer(TransferDTO transferDTO){

        try {
        ExecuteTransferCmd command = new ExecuteTransferCmd(transferDTO.getOriginAccountId(),
                transferDTO.getTargetAccountId(), transferDTO.getAmount());

            Object result =
                    ask(commandHandler, command, Duration.ofMillis(1000)).toCompletableFuture().join();

            if(result instanceof Transfer){
                return new ConfirmationDTO("OK", "Transaction sucess");
            }else if(result instanceof TransferErrorNtf){
                return new TransferError("Error",((TransferErrorNtf) result).getMessage());
            }else{
                return new TransferError("Error","Unknown  error");
            }
        }catch(Exception e){
            return new TransferError("Error",e.getMessage());
        }

    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionListDTO getTransactionsByAccountId(@PathParam("accountId") String accountId){
        List<TransferDTO> transferDTOStream = repository.getTransactionsByAccountId(accountId).join().stream().map(t ->
                new TransferDTO(t.getFrom(), t.getTo(), t.getAmount(), t.getStatus().toString(), t.getDateTime())
        ).collect(Collectors.toList());

        return new TransactionListDTO(new ArrayList<TransferDTO>(transferDTOStream));

    }

}
