package com.grabsy.GrabsyBackend.exception.user.attribute;

public class InvalidUserIdException extends ValidationException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
