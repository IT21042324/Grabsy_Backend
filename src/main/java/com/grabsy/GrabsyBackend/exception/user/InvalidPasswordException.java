package com.grabsy.GrabsyBackend.exception.user;

public class InvalidPasswordException extends ValidationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
