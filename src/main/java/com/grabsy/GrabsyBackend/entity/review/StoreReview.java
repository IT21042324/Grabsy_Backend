package com.grabsy.GrabsyBackend.entity.review;

import com.grabsy.GrabsyBackend.domain.Review;

public class StoreReview extends Review {
    private String storeId;

    protected StoreReview() {
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "StoreReview{" + "storeId='" + storeId + '\'' + ", " + super.toString() + '}';
    }
}
