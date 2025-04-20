package com.grabsy.GrabsyBackend.dto;

import java.util.List;

/**
 * This class is a Data Transfer Object (DTO) for sellers.
 */

public class SellerDto extends SignedUserDto{
    private String storeId;
    private List<String> productsListed;
    private Double sellerRating;

    public SellerDto() {
    }

    public SellerDto(String storeId, List<String> productsListed, Double sellerRating) {
        this.storeId = storeId;
        this.productsListed = productsListed;
        this.sellerRating = sellerRating;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

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
}
