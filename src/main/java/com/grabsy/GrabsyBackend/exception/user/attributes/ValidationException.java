package com.grabsy.GrabsyBackend.exception.user.attributes;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
