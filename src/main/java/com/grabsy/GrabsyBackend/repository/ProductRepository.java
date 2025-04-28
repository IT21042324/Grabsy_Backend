package com.grabsy.GrabsyBackend.repository;

import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{_id: 0}", fields = "{reviews:  1}")
    Product findAllReviewsByProductId(String id);
}
