package com.grabsy.GrabsyBackend.repository;

import com.grabsy.GrabsyBackend.entity.UserIdCounter;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface is used to interact with the userIdCounter collection in the MongoDB database. It extends MongoRepository to provide CRUD operations
 */

public interface UserIdCounterRepository extends MongoRepository<UserIdCounter, String> {

}
