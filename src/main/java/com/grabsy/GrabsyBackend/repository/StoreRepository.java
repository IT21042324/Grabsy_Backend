package com.grabsy.GrabsyBackend.repository;

import com.grabsy.GrabsyBackend.entity.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}
