package org.challenge.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.challenge.domain.User;
import org.challenge.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

    @Inject
    UserService userService;

    @POST
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}