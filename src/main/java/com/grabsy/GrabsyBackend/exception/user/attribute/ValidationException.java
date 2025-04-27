package com.grabsy.GrabsyBackend.exception.user.attribute;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
