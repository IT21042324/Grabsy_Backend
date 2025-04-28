package com.grabsy.GrabsyBackend.service.review;

import com.grabsy.GrabsyBackend.entity.review.StoreReview;
import com.grabsy.GrabsyBackend.repository.review.StoreReviewRepository;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;

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
    public List<StoreReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId, String sortingProperty, Sort.Direction direction) {
        return null;
    }

    @Override
    public EntityModel<StoreReview> updateReview(String id, StoreReview review) {
        return null;
    }

    @Override
    public Boolean deleteReview(String id) {
        return null;
    }
}
