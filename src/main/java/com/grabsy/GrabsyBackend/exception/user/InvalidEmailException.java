package com.grabsy.GrabsyBackend.exception.user;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
