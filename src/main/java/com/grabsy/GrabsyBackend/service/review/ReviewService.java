package com.grabsy.GrabsyBackend.service.review;
import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.domain.Review;
import com.grabsy.GrabsyBackend.entity.review.StoreReview;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ReviewService<T extends Review> {
    List<T> getAllReviewsOfOneUser(String userId);

    List<T> findReviewsMadeByUserToReviewable(String userId, String reviewableId);

    List<T> findReviewsMadeByUserToReviewable(String userId, String reviewableId, String sortingProperty);

    List<T> findReviewsMadeByUserToReviewable(String userId, String reviewableId,
                                                          String sortingProperty, Sort.Direction direction);

    EntityModel<T> updateReview(String id, T review);

    Boolean deleteReview(String id);
}
