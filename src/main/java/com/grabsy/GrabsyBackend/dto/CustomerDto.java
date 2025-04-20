package com.grabsy.GrabsyBackend.dto;

import com.grabsy.GrabsyBackend.model.Card;

import java.util.List;

/**
 * This class is a Data Transfer Object (DTO) for customers.
 */

public class CustomerDto extends SignedUserDto{
    private String shippingAddress;
    private List<Card> paymentCards;

    public CustomerDto(){}

    public CustomerDto(String shippingAddress, List<Card> paymentCards) {
        this.shippingAddress = shippingAddress;
        this.paymentCards = paymentCards;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Card> getPaymentCards() {
        return paymentCards;
    }

    public void setPaymentCards(List<Card> paymentCards) {
        this.paymentCards = paymentCards;
    }
}
