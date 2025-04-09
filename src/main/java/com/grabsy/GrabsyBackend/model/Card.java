package com.grabsy.GrabsyBackend.model;

import org.springframework.data.annotation.Id;

/**
 * The Card model represent the structure of a card (debit/credit) a signed user in the system may hold.
 */

public class Card {
    @Id
    private String cardNumber;
    private String expiryDate;

    //constructors
    public Card(){}

    public Card(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    // getters and setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
