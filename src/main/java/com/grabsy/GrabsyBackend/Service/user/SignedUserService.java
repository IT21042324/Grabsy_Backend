package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.exception.user.UserDeletionException;
import com.grabsy.GrabsyBackend.exception.user.UserFetchException;
import com.grabsy.GrabsyBackend.exception.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This class is a placeholder for the SignedUserService, it contains business logic related to signed users.
 */

public abstract class SignedUserService {
    private static final Logger log = LoggerFactory.getLogger(SignedUserService.class);

    protected <T> T getUserById(String userId, MongoRepository<T, String> repository){
        try {
            if (!repository.existsById(userId)){
                log.error("User with id: {} could not be found", userId);
                throw new UserNotFoundException("User with id: " + userId + " could not be found");
            }

            repository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " could not be found"));
        } catch (DataAccessException e) {
            log.error("Error fetching user with id: {}", userId, e);
            throw new UserFetchException("User with id: "  + userId + " could not be fetched", e);
        }
    }

    protected <T> List<T> getAllUsersByRole(MongoRepository<T, String> repository){
        try{
            return repository.findAll();
        } catch (DataAccessException e){
            log.error("Error fetching users from repository", e);
            throw new UserFetchException("Unable to fetch users from repository", e);
        }
    }

    protected <T> void deleteUserById(String userId, MongoRepository<T, String> repository){
        try {
            if(!repository.existsById(userId)){
                log.error("User with id: {} could not be found", userId);
                throw new UserNotFoundException("User with id: " + userId + " could not be found");
            }

            repository.deleteById(userId);
        } catch (DataAccessException e) {
            log.error("Error deleting user with id: {}", userId, e);
            throw new UserDeletionException("User with id : " + userId + " could not be deleted", e);
        }
    }
}
