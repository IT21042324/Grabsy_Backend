package com.grabsy.GrabsyBackend.exception.user.attribute;

public class InvalidPasswordException extends ValidationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
