package com.grabsy.GrabsyBackend.exception;

/**
 * This class is a custom exception that is thrown when a customer is not found.
 */

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String userId) {
        super("Could not find customer " + userId);
    }
}
