package org.challenge.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.challenge.domain.Account;
import org.challenge.domain.User;
import org.challenge.service.AccountService;
import org.challenge.service.UserService;

@Path("/accounts")
public class AccountRest {

    @Inject
    UserService userService;

    @Inject
    AccountService accountService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createAccountForUser(Account account) {
        // Check if the user exists
        User user = userService.findById(account.getUser().getUserId());
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User does not exist").build();
        }

        account.setUser(user);
        accountService.createAccount(account);

        return Response.status(Response.Status.CREATED).entity(account).build();
    }
}
