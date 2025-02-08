package org.challenge.rest.webhooks;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/webhooks")
public class CVVUpdateRest {

    @ConfigProperty(name = "api.key") // A API Key para autenticação
    String apiKey;

    @POST
    @Path("/cvv-update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processCVVUpdateWebhook(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            CVVUpdate cvvUpdateInfo) {

        if (authorizationHeader == null || !authorizationHeader.equals("ApiKey " + apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid API Key").build();
        }

        System.out.println("Received CVV update webhook: " + cvvUpdateInfo);

        // Atualizar CVV cartão

        return Response.ok().build();
    }


}
