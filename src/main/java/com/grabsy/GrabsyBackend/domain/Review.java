package com.grabsy.GrabsyBackend.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

import static com.grabsy.GrabsyBackend.contant.ReviewConstant.*;

@Document(collection = "reviews")
public abstract class Review {
    @Id
    private String id;

    @NotBlank(message = REVIEW_DESCRIPTION_REQUIRED)
    private String reviewDescription;

    @NotBlank(message = USER_ID_REQUIRED)
    private String userId;

    @NotNull(message =  RATING_REQUIRED)
    @Min(value = 1, message = RATING_VALIDATION)
    @Max(value = 5, message = RATING_VALIDATION)
    private Byte ratings;
    @CreatedDate
    @Field("createdAt")
    private Instant createdAt;
    @LastModifiedDate
    @Field("updatedAt")
    private Instant updatedAt;

    protected Review(){}

    protected Review(String reviewDescription, String userId, Byte ratings) {
        this.reviewDescription = reviewDescription;
        this.userId = userId;
        this.ratings = ratings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Byte getRatings() {
        return ratings;
    }

    public void setRatings(Byte ratings) {
        this.ratings = ratings;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
