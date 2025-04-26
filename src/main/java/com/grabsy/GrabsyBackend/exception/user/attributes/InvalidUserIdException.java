package com.grabsy.GrabsyBackend.exception.user.attributes;

public class InvalidUserIdException extends ValidationException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
