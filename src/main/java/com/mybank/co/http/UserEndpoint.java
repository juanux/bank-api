package com.mybank.co.http;

import com.mybank.co.bank.Account;
import com.mybank.co.bank.service.IAccountService;
import com.mybank.co.http.dto.AccountDTO;
import com.mybank.co.http.dto.DTO;
import com.mybank.co.http.dto.UserDTO;
import com.mybank.co.http.dto.error.InvalidUserError;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.UUID;

@Path("user")
public class UserEndpoint {

    @Inject
    IAccountService service;

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") String id) {
        String userId;
        try{
            userId = UUID.fromString(id).toString();
        }catch (Exception e){
            return Response.status(400).entity(new InvalidUserError("400","Invalid user id")).build();
        }

        Optional<Account> account = service.getByUserId(userId);

        if(account.isPresent()){
            return Response.status(200).entity(accountToDTO(account.get())).build();
        }else{
            return Response.status(404).entity(new InvalidUserError("404","User not found.")).build();
        }
    }

    @GET
    @Path("/account/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByAccount(@PathParam("accountId") String id) {

        Optional<Account> account = service.getByAccountId(id);

        if(account.isPresent()){
            return Response.status(200).entity(accountToDTO(account.get())).build();
        }else{
            return Response.status(404).entity(new InvalidUserError("404","User not found.")).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO user) {

        try {
            return Response.status(200).entity(accountToDTO(service.createAccount(user))).build();
        }catch(Exception error){
            System.out.println("Error : " + error.getMessage());
            return Response.status(400).entity(new InvalidUserError("400",error.getMessage())).build();
        }

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO updateUser(UserDTO user) {

        return new UserDTO();
    }

    @DELETE
    @Path("{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO deleteUser(@PathParam("accountId") String accountId) {

        Account account = service.deleteAccount(accountId);

        return new UserDTO();
    }

    private UserDTO accountToDTO(Account account){

        return new UserDTO(account.getUser().getId(),
                account.getUser().getName(),
                account.getUser().getLastName(),
                account.getUser().getDocumentType().toString(),
                account.getUser().getDocumentId(),
                account.getUser().getEmail(),
                account.getUser().getBirthDay(),
                account.getUser().getGender().toString(),
                new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getCurrency().toString()));

    }
}
