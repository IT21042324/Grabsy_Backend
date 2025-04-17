package com.grabsy.GrabsyBackend.entity.users;

import com.grabsy.GrabsyBackend.domain.SignedUser;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * The Seller entity represent the structure of a seller in the database, it maps to "sellers" collection .
 */

@Document(collection = "sellers")
public class Seller extends SignedUser {
    private List<String> productsListed;
    private Double sellerRating;
    private String storeId;

    // constructors
    public Seller(List<String> productsListed, Double sellerRating, String storeId) {}

    public Seller(String userId, String userRole, String name, String email, String passwordHash, String phoneNumber, Date registrationDate, List<String> productsListed, Double sellerRating, String storeId) {
        super(userId, userRole, name, email, passwordHash, phoneNumber, registrationDate);
        this.productsListed = productsListed;
        this.sellerRating = sellerRating;
        this.storeId = storeId;
    }

    // getters and setters
    public List<String> getProductsListed() {
        return productsListed;
    }

    public void setProductsListed(List<String> productsListed) {
        this.productsListed = productsListed;
    }

    public Double getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Double sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
