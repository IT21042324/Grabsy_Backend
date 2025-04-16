package com.grabsy.GrabsyBackend.Entity.Review;

import com.grabsy.GrabsyBackend.domain.Review;

public class StoreReview extends Review {
    private String storeId;

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
