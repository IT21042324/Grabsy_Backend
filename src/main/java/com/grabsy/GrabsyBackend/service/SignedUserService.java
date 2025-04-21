package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.exception.UserNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This class is a placeholder for the SignedUserService, it contains business logic related to signed users.
 */

public abstract class SignedUserService {

    protected <T> T getUserById(String userId, MongoRepository<T, String> repository){
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    protected <T> List<T> getAllUsersByRole(MongoRepository<T, String> repository){
        return repository.findAll();
    }
}
