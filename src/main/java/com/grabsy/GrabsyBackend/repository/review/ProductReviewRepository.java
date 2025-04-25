package com.grabsy.GrabsyBackend.repository.review;

import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductReviewRepository extends ReviewRepository<ProductReview> {
    @Query("{'userId': ?0, 'productId':  ?1}")
    List<ProductReview> findReviewsMadeByUserToProduct(String userId, String productId);

    @Query("{'userId': ?0, 'productId':  ?1}")
    List<ProductReview> findReviewsMadeByUserToProduct(String userId, String productId, Sort sort);
}
