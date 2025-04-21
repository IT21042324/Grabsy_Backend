package com.grabsy.GrabsyBackend.exception;

/**
 * This class is a custom exception that is thrown when a user is not found.
 */

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
