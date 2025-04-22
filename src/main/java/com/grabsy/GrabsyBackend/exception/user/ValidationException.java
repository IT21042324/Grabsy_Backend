package com.grabsy.GrabsyBackend.exception.user;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
