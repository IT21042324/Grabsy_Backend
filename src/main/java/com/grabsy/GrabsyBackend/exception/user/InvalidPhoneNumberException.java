package com.grabsy.GrabsyBackend.exception.user;

public class InvalidPhoneNumberException extends ValidationException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
