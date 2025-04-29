package com.grabsy.GrabsyBackend.exception.user;

public class InvalidShippingAddressException extends ValidationException {
    public InvalidShippingAddressException(String message) {
        super(message);
    }
}
