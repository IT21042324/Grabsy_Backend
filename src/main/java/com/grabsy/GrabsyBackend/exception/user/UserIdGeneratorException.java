package com.grabsy.GrabsyBackend.exception.user;

public class UserIdGeneratorException extends RuntimeException {
    public UserIdGeneratorException(String message) {
        super(message);
    }

    public UserIdGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
