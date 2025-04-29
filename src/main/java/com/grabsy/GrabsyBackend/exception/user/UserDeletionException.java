package com.grabsy.GrabsyBackend.exception.user;

/**
 * Custom exception class for handling user deletion errors.
 */

public class UserDeletionException extends UserException {
    public UserDeletionException(String message) {
        super(message);
    }

    public UserDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
