package com.grabsy.GrabsyBackend.exception.user;

public class UserSaveException extends UserException {
    public UserSaveException(String message) {
        super(message);
    }

    public UserSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
