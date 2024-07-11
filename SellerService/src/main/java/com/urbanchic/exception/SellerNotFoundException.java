package com.urbanchic.exception;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException(String message){
        super(message);
    }
}
