package com.grabsy.GrabsyBackend.Entity.Review;

import com.grabsy.GrabsyBackend.Entity.MainEntity;
import com.grabsy.GrabsyBackend.domain.Review;

import java.util.List;

public interface Reviewable{
    List<Review> getReviews();
    void setReviews(List<Review> reviewList);

    default Byte getAverageRating(){
        List<Review> reivewList = getReviews();

        Long rating = 0L;
        for (Review review : reivewList) {
            rating += review.getRatings();
        }

        return (byte) (rating/reivewList.size());
    }
}
