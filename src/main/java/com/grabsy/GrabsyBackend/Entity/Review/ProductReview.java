package com.grabsy.GrabsyBackend.Entity.Review;

import com.grabsy.GrabsyBackend.domain.Review;

public class ProductReview extends Review {
    private String productId;

    public ProductReview() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductReview{" + "product='" + productId + '\'' + ", " + super.toString() + '}';
    }
}
