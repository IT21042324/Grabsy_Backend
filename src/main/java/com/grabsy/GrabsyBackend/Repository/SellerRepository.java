package com.grabsy.GrabsyBackend.Repository;

import com.grabsy.GrabsyBackend.Entity.User.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface is a repository for the Seller entity, it extends MongoRepository to provide CRUD operations.
 */

public interface SellerRepository extends MongoRepository<Seller, String> {
}
