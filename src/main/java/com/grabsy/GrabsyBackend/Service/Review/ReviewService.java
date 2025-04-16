package com.grabsy.GrabsyBackend.Service.Review;
import com.grabsy.GrabsyBackend.Entity.Review.ProductReview;
import com.grabsy.GrabsyBackend.Entity.Review.StoreReview;
import com.grabsy.GrabsyBackend.Repository.Review.ProductReviewRepository;
import com.grabsy.GrabsyBackend.Repository.Review.ReviewRepository;
import com.grabsy.GrabsyBackend.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService<T extends Review> {

    List<T> getAllReviewsOfOneUser(String userId);

    List<T> findReviewsMadeByUserToReviewable(String userId, String reviewableId);

    List<T> findReviewsMadeByUserToReviewable(String userId, String reviewableId, String sortingProperty);

    List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId,
                                                          String sortingProperty, Sort.Direction direction);

    T updateReview(String id, T review);

    Boolean deleteReview(String id);
}
