package com.grabsy.GrabsyBackend.repository.user;

import com.grabsy.GrabsyBackend.entity.users.Seller;

/**
 * This interface is a repository for the Seller entity, it extends MongoRepository to provide CRUD operations.
 */

public interface SellerRepository extends SignedUserRepository<Seller, String>, EmailRepository {
    boolean existsByEmail(String email);
}
