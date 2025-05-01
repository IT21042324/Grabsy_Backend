package com.grabsy.GrabsyBackend.exception.user.attribute;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
