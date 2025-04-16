package com.grabsy.GrabsyBackend.Service.Review;

import com.grabsy.GrabsyBackend.Entity.Review.ProductReview;
import com.grabsy.GrabsyBackend.Entity.Review.StoreReview;
import com.grabsy.GrabsyBackend.Repository.Review.StoreReviewRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class StoreReviewService implements ReviewService<StoreReview> {
    private final StoreReviewRepository storeReviewRepository;
    public StoreReviewService(StoreReviewRepository reviewRepository) {
        storeReviewRepository = reviewRepository;
    }

    public StoreReview save(StoreReview review) {
        return null;
    }

    @Override
    public List<StoreReview> getAllReviewsOfOneUser(String userId) {
        return null;
    }

    @Override
    public List<StoreReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId) {
        return null;
    }

    @Override
    public List<StoreReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId, String sortingProperty) {
        return null;
    }

    @Override
    public List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId, String sortingProperty, Sort.Direction direction) {
        return null;
    }

    @Override
    public StoreReview updateReview(String id, StoreReview review) {
        return null;
    }

    @Override
    public Boolean deleteReview(String id) {
        return null;
    }
}
