package com.grabsy.GrabsyBackend.exception.user;

public class InvalidNameException extends ValidationException {
    public InvalidNameException(String message) {
        super(message);
    }
}
