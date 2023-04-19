package com.dezzy.exception;

public class ResourceNotFoundException extends RuntimeException{
    String errorMessage;
    String path;
    public ResourceNotFoundException(String message, String path){
        this.errorMessage = message;
        this.path = path;

    }
}
