package com.grabsy.GrabsyBackend.exception;

/**
 * Custom exception class for handling user-related errors.
 */

public class UserException extends RuntimeException {
    public UserException(String message){
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
