package com.grabsy.GrabsyBackend.exception;

/**
 * This class is a custom exception that is thrown when a seller is not found.
 */

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(String userId) {
        super("Could not find Seller " + userId);
    }
}
