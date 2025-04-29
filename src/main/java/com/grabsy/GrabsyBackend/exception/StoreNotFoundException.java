package com.grabsy.GrabsyBackend.exception;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(String id){
        super(String.format("Could not find product of id: %s", id));
    }
}
