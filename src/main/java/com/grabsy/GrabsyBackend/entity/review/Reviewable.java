package com.grabsy.GrabsyBackend.entity.review;

import java.util.List;
import java.util.stream.Stream;

public interface Reviewable {
    List<String> getReviews();

    void setReviews(List<String> reviewList);

    void setAverageRating(byte averageRating);

    Byte getAverageRating();

    default void addReviews(String newReview) {
        List<String> existingReviews = getReviews();
        existingReviews.add(newReview);
        setReviews(existingReviews);
    }

    default void addReviews(List<String> newReviews) {
        List<String> existingReviews = getReviews();
        List<String> combinedListOfReviews = Stream.concat(existingReviews.stream(), newReviews.stream()).toList();
        setReviews(combinedListOfReviews);
    }

    default void updateAverageRating(int newRating) {
        int existingTotalNumberOfReviews = getReviews() != null ? getReviews().size() : 0;
        setAverageRating((byte) (newRating + (getAverageRating() * existingTotalNumberOfReviews) /
                existingTotalNumberOfReviews + 1));
    }
}
