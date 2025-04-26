package com.grabsy.GrabsyBackend.exception.user.attributes;

public class InvalidShippingAddressException extends ValidationException {
    public InvalidShippingAddressException(String message) {
        super(message);
    }
}
