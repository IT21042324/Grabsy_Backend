package com.grabsy.GrabsyBackend.entity;
import com.grabsy.GrabsyBackend.entity.review.Reviewable;
import com.grabsy.GrabsyBackend.domain.Review;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "stores")
public class Store implements Reviewable {
    @Id
    private String id;
    private String sellerId;
    private List<String> reviews;
    private Byte averageRating;

    public Store() {
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

    @Override
    public List<String> getReviews() {
        return this.reviews;
    }

    @Override
    public void setReviews(List<String> reviewList) {
        this.reviews = reviewList;
    }

    @Override
    public Byte getAverageRating() {
        return averageRating;
    }

    @Override
    public void setAverageRating(byte averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
