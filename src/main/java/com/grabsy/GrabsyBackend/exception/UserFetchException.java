package com.grabsy.GrabsyBackend.exception;

public class UserFetchException extends UserException {
    public UserFetchException(String message){
        super(message);
    }

    public UserFetchException(String message, Throwable cause){
        super(message, cause);
    }
}
