package com.grabsy.GrabsyBackend.exception.user.attributes;

import com.grabsy.GrabsyBackend.exception.user.ValidationException;

public class InvalidPhoneNumberException extends ValidationException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
