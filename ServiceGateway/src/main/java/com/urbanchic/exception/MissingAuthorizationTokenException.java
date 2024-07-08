package com.urbanchic.exception;

public class MissingAuthorizationTokenException extends RuntimeException{
    public MissingAuthorizationTokenException(String message) {
        super(message);
    }
}
