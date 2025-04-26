package com.grabsy.GrabsyBackend.exception.user.attributes;

public class InvalidPasswordException extends ValidationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
