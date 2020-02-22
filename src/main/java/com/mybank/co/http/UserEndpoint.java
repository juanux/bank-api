package com.mybank.co.http;

import com.mybank.co.http.dto.DTO;
import com.mybank.co.http.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserEndpoint {

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public DTO getUserById(@PathParam("userId") String id) {

        return new UserDTO();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO createUser(UserDTO user) {

        return new UserDTO();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO updateUser(UserDTO user) {

        return new UserDTO();
    }

    @DELETE
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DTO deleteUser(@PathParam("userId") String id) {

        return new UserDTO();
    }
}
