package com.grabsy.GrabsyBackend.exception.user.attribute;

public class InvalidShippingAddressException extends ValidationException {
    public InvalidShippingAddressException(String message) {
        super(message);
    }
}
