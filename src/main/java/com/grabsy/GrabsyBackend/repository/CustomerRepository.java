package com.grabsy.GrabsyBackend.repository;

import com.grabsy.GrabsyBackend.entity.users.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface is a repository for the Customer entity, it extends MongoRepository to provide CRUD operations.
 */

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
