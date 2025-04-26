package com.grabsy.GrabsyBackend.exception.user.attributes;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
