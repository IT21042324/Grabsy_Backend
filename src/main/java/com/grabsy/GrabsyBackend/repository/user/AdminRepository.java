package com.grabsy.GrabsyBackend.repository.user;

import com.grabsy.GrabsyBackend.entity.users.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface is a repository for the Admin entity, it extends MongoRepository to provide CRUD operations.
 */

public interface AdminRepository extends SignedUserRepository<Admin, String>, EmailRepository {
    boolean existsByEmail(String email);
}
