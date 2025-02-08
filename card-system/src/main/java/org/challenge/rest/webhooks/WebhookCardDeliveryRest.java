package org.challenge.rest.webhooks;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.challenge.service.CardService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class WebhookCardDeliveryRest {

    @ConfigProperty(name = "api.key") // A API Key para autenticação
    String apiKey;

    @Inject
    CardService cardService;

    @POST
    @Path("/delivery")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processCardDeliveryWebhook(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            DeliveryCard deliveryCard) {

        if (authorizationHeader == null || !authorizationHeader.equals("ApiKey " + apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid API Key").build();
        }

        System.out.println("Received delivery webhook: " + deliveryCard);

        // vvalidar e atualizar o status da entrega no banco de dados

        return Response.ok().build();
    }

}

