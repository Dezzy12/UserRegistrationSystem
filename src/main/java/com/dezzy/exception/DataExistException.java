package com.dezzy.exception;

public class DataExistException extends RuntimeException{
    String errorMessage;
    String path;

    public DataExistException(String message, String path){
        this.errorMessage = message;
        this.path = path;
    }
}
