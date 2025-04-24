package com.grabsy.GrabsyBackend.exception.user;

public class InvalidUserIdException extends ValidationException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
