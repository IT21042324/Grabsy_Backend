package com.grabsy.GrabsyBackend.exception;

public class UserDeletionException extends UserException {
    public UserDeletionException(String message) {
        super(message);
    }

    public UserDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
