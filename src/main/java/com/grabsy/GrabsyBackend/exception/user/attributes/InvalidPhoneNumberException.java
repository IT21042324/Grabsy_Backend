package com.grabsy.GrabsyBackend.exception.user.attributes;

public class InvalidPhoneNumberException extends ValidationException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
