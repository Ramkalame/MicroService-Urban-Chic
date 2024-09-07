package com.urbanchic.exception;

public class ImageUploadFailedException extends RuntimeException{

    public ImageUploadFailedException(String message) {
        super(message);
    }
}
