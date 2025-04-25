package com.grabsy.GrabsyBackend.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String msg){
        super(String.format("trace: %s", msg));
    }
}
