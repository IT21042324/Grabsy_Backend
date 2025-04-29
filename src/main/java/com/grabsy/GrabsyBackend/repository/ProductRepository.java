package com.grabsy.GrabsyBackend.repository;

import com.grabsy.GrabsyBackend.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{_id: 0}", fields = "{reviews:  1}")
    Product findAllReviewsByProductId(String id);
}
