package com.grabsy.GrabsyBackend.Repository.Review;


import com.grabsy.GrabsyBackend.Entity.Product;
import com.grabsy.GrabsyBackend.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository<T extends Review> extends MongoRepository<T, String> {
    List<T> findByUserId(String userId);
}
