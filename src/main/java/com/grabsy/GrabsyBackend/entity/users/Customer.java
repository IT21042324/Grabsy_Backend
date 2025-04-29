package com.grabsy.GrabsyBackend.entity.users;

import com.grabsy.GrabsyBackend.domain.SignedUser;
import com.grabsy.GrabsyBackend.dto.Card;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Customer entity represent the structure of a customer in the database, it maps to "customers" collection .
 */

@Document(collection = "customers")
public class Customer extends SignedUser {
    private String shippingAddress;
    private List<Card> paymentCards;

    // constructors
    public Customer(){}

    public Customer(String userId, String userRole, String name, String email, String passwordHash, String phoneNumber,
                    LocalDateTime registrationDate) {
        super(userId, userRole, name, email, passwordHash, phoneNumber, registrationDate);
    }

    // getters and setters
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
