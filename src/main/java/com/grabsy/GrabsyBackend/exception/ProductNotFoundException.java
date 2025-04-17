package com.grabsy.GrabsyBackend.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id){
        super(String.format("Could not find product of id: %s", id));
    }
}
