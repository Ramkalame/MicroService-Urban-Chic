package com.urbanchic.exception;

public class IncorrectPasswordException extends  RuntimeException{
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
