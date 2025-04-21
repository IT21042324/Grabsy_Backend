package com.grabsy.GrabsyBackend.exception;

/**
 * Custom exception class for handling user fetch errors.
 */

public class UserFetchException extends UserException {
    public UserFetchException(String message){
        super(message);
    }

    public UserFetchException(String message, Throwable cause){
        super(message, cause);
    }
}
