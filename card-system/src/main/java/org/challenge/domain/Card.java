package org.challenge.domain;

import jakarta.persistence.*;
import org.challenge.enums.ActiveCard;
import org.challenge.enums.CancelReason;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private Account account;

    @Column(name = "card_number", columnDefinition = "VARBINARY(255)", nullable = false)
    private byte[] cardNumber; // Encrypted card number

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "cvv", columnDefinition = "VARBINARY(3)", nullable = false)
    private byte[] cvv; // Encrypted CVV

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "delivered", columnDefinition = "VARCHAR(1)")
    private String delivered;

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private ActiveCard active; // Store 'Y' or 'N'

    @Enumerated(EnumType.STRING)
    @Column(name = "cancelReason")
    private CancelReason cancelReason;

    // Getters and Setters

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public byte[] getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(byte[] cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public byte[] getCvv() {
        return cvv;
    }

    public void setCvv(byte[] cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public ActiveCard getActive() {
        return active;
    }

    public void setActive(ActiveCard active) {
        this.active = active;
    }

    public CancelReason getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(CancelReason cancelReason) {
        this.cancelReason = cancelReason;
    }
}
