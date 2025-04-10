package com.grabsy.GrabsyBackend.Repository;

import com.grabsy.GrabsyBackend.Entity.users.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface is a repository for the Customer entity, it extends MongoRepository to provide CRUD operations.
 */

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
