package com.grabsy.GrabsyBackend.entity.review;

import com.grabsy.GrabsyBackend.domain.Review;

import java.util.List;

public interface Reviewable {
    List<Review> getReviews();
    void setReviews(List<Review> reviewList);
    void setAverageRating(byte averageRating);

    default void calculateAverageRating() {
        List<Review> reviewList = getReviews();
        byte averageRating = 0;
        long rating = 0L;

        if (reviewList != null && !reviewList.isEmpty()){
            for (Review review : reviewList)
                rating += review.getRatings();

            averageRating = (byte) (rating / reviewList.size());
        }
        setAverageRating(averageRating);
    }
}
