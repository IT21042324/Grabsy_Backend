package com.grabsy.GrabsyBackend.Repository.Review;
import com.grabsy.GrabsyBackend.Entity.Review.StoreReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface StoreReviewRepository extends ReviewRepository<StoreReview> {
    @Query("{'userId': ?0, 'reviewableId':  ?1}")
    List<StoreReview> findReviewsMadeByUserToStore(String userId, String storeId);

    @Query("{'userId': ?0, 'reviewableId':  ?1}")
    List<StoreReview> findReviewsMadeByUserToStore(String userId, String storeId, Sort sort);
}
