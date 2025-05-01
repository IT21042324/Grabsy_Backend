package com.grabsy.GrabsyBackend.response;

import com.grabsy.GrabsyBackend.entity.*;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public class StoreResponse {
    private String id;
    private String sellerId;

    private List<EntityModel<ResponseId>> reviews;
    private Byte averageRating;

    public StoreResponse(Store store) {
        this.id = store.getId();
        this.averageRating = store.getAverageRating();
        this.sellerId = store.getSellerId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<EntityModel<ResponseId>> getReviews() {
        return reviews;
    }

    public void setReviews(List<EntityModel<ResponseId>> reviews) {
        this.reviews = reviews;
    }

    public Byte getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Byte averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "StoreResponse{" +
                "id='" + id + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", reviews=" + reviews.toString() +
                ", averageRating=" + averageRating +
                '}';
    }
}
