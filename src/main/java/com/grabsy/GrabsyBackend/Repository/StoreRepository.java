package com.grabsy.GrabsyBackend.Repository;

import com.grabsy.GrabsyBackend.Entity.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}
