package com.grabsy.GrabsyBackend.exception.user.attributes;

import com.grabsy.GrabsyBackend.exception.user.ValidationException;

public class InvalidShippingAddressException extends ValidationException {
    public InvalidShippingAddressException(String message) {
        super(message);
    }
}
