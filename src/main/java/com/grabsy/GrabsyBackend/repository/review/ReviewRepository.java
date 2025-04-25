package com.grabsy.GrabsyBackend.repository.review;


import com.grabsy.GrabsyBackend.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository<T extends Review> extends MongoRepository<T, String> {
    List<T> findByUserId(String userId);
}
