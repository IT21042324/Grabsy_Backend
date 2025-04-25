package com.grabsy.GrabsyBackend.exception.user.attributes;

import com.grabsy.GrabsyBackend.exception.user.ValidationException;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
