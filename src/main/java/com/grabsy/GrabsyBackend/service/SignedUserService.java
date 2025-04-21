package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.exception.UserDeletionException;
import com.grabsy.GrabsyBackend.exception.UserFetchException;
import com.grabsy.GrabsyBackend.exception.UserNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This class is a placeholder for the SignedUserService, it contains business logic related to signed users.
 */

public abstract class SignedUserService {

    protected <T> T getUserById(String userId, MongoRepository<T, String> repository){
        try {
            if (!repository.existsById(userId)){
                throw new UserNotFoundException("User with id: " + userId + " could not be found");
            }
            return repository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " could not be found"));
        } catch (DataAccessException e) {
            throw new UserFetchException("User with id: "  + userId + " could not be fetched", e);
        }
    }

    protected <T> List<T> getAllUsersByRole(MongoRepository<T, String> repository){
        try{
            return repository.findAll();
        } catch (DataAccessException e){
            throw new UserFetchException("Unable to fetch users from repository", e);
        }
    }

    protected <T> void deleteUserById(String userId, MongoRepository<T, String> repository){
        try {
            if(!repository.existsById(userId)){
                throw new UserNotFoundException("User with id: " + userId + " could not be found");
            }

            repository.deleteById(userId);
        } catch (DataAccessException e) {
            throw new UserDeletionException("User with id : " + userId + " could not be deleted", e);
        }
    }
}
