package com.grabsy.GrabsyBackend.exception.user.attribute;

public class InvalidPhoneNumberException extends ValidationException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
