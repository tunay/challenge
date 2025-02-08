package org.challenge.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.challenge.dto.CardDto;
import org.challenge.domain.Account;
import org.challenge.domain.Card;
import org.challenge.domain.CardActivityLog;
import org.challenge.service.AccountService;
import org.challenge.service.CardActivityLogService;
import org.challenge.service.CardService;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardRest {

    @Inject
    CardService cardService;

    @Inject
    AccountService accountService;

    @Inject
    CardActivityLogService cardActivityLogService;

    @POST
    @Transactional
    public Response createCard(CardDto cardDto) {
        // Find account by ID
        Account account = accountService.getAccountById(cardDto.accountId);

        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Account not found").build();
        }

        byte[] encryptedCardNumber = encryptCardDetails(cardDto.cardNumber);
        byte[] encryptedCvv = encryptCardDetails(cardDto.cvv);

        Card card = new Card();
        card.setAccount(account);
        card.setCardNumber(encryptedCardNumber);
        card.setCvv(encryptedCvv);
        card.setExpirationDate(cardDto.expirationDate);
        cardService.saveCard(card);

        CardActivityLog cardLog = new CardActivityLog();
        cardLog.setCard(card);
        cardLog.setAction("Card Created");
        cardLog.setNotes("New card created.");
        cardActivityLogService.saveCardActivityLog(cardLog);

        return Response.created(UriBuilder.fromResource(CardRest.class).path(String.valueOf(card.getCardId())).build()).build();
    }

    @DELETE
    @Path("/{cardNumber}/cancel")
    @Produces("application/json")
    public Response cancelCard(@PathParam("cardNumber") Long cardId) {
        Card card = cardService.getCard(cardId);
        if(card !=  null) {
            boolean success = cardService.cancelCardByCard(card);

            if (success) {
                return Response.ok().entity("{\"message\":\"Card canceled successfully\"}").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Failed to cancel the card\"}")
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Failed to cancel the card, card not found\"}")
                    .build();
        }

    }

    private byte[] encryptCardDetails(String cardDetail) {
        return cardDetail.getBytes();
    }
}