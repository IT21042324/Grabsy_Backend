package com.grabsy.GrabsyBackend.exception.user.attributes;

import com.grabsy.GrabsyBackend.exception.user.ValidationException;

public class InvalidNameException extends ValidationException {
    public InvalidNameException(String message) {
        super(message);
    }
}
