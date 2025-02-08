package org.challenge.rest;

import jakarta.ws.rs.core.Response;
import org.challenge.dto.CardDto;
import org.challenge.domain.Account;
import org.challenge.domain.Card;
import org.challenge.service.AccountService;
import org.challenge.service.CardActivityLogService;
import org.challenge.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CardRestTest {

    @InjectMocks
    private CardRest cardRest;  // Class under test

    @Mock
    private CardService cardService;

    @Mock
    private AccountService accountService;

    @Mock
    private CardActivityLogService cardActivityLogService;

    private Account mockAccount;
    private CardDto mockCardDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Account
        mockAccount = new Account();
        mockAccount.setAccountId(1L);

        // Mock CardDto
        mockCardDto = new CardDto();
        mockCardDto.setAccountId(1L);
        mockCardDto.setCardNumber("4111111111111111");
        mockCardDto.setCvv("123");
        mockCardDto.setExpirationDate(LocalDate.now());
    }

    @Test
    public void testCreateCard_Success() {
        // Arrange
        when(accountService.getAccountById(mockCardDto.getAccountId())).thenReturn(mockAccount);

        Card mockCard = new Card();
        mockCard.setCardId(1L);
        mockCard.setCardNumber(mockCardDto.getCardNumber().getBytes());
        mockCard.setCvv(mockCardDto.getCvv().getBytes());
        mockCard.setExpirationDate(mockCardDto.getExpirationDate());

        when(cardService.saveCard(any(Card.class))).thenReturn(mockCard);

        // Act
        Response response = cardRest.createCard(mockCardDto);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getLocation());  // Check if the Location header is set correctly.
    }

    @Test
    public void testCreateCard_AccountNotFound() {
        // Arrange
        when(accountService.getAccountById(mockCardDto.getAccountId())).thenReturn(null);

        // Act
        Response response = cardRest.createCard(mockCardDto);

        // Assert
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("Account not found", response.getEntity());
    }

    @Test
    public void testCancelCard_Success() {
        // Arrange
        Card mockCard = new Card();
        mockCard.setCardId(1L);
        when(cardService.getCard(1L)).thenReturn(mockCard);
        when(cardService.cancelCardByCard(mockCard)).thenReturn(true);

        // Act
        Response response = cardRest.cancelCard(1L);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("{\"message\":\"Card canceled successfully\"}", response.getEntity());
    }

    @Test
    public void testCancelCard_CardNotFound() {
        // Arrange
        when(cardService.getCard(1L)).thenReturn(null);

        // Act
        Response response = cardRest.cancelCard(1L);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("{\"message\":\"Failed to cancel the card, card not found\"}", response.getEntity());
    }

    @Test
    public void testCancelCard_Failure() {
        // Arrange
        Card mockCard = new Card();
        mockCard.setCardId(1L);
        when(cardService.getCard(1L)).thenReturn(mockCard);
        when(cardService.cancelCardByCard(mockCard)).thenReturn(false);

        // Act
        Response response = cardRest.cancelCard(1L);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("{\"message\":\"Failed to cancel the card\"}", response.getEntity());
    }
}

