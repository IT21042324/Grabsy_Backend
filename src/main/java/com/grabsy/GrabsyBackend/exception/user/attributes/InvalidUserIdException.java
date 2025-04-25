package com.grabsy.GrabsyBackend.exception.user.attributes;

import com.grabsy.GrabsyBackend.exception.user.ValidationException;

public class InvalidUserIdException extends ValidationException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
