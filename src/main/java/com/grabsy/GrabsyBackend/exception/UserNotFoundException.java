package com.grabsy.GrabsyBackend.exception;

/**
 * This class is a custom exception that is thrown when a user is not found.
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("Could not find user " + userId);
    }
}
