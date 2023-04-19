package com.dezzy.exception;

import com.dezzy.dto.UserDTO;

public class CustomErrorType{
    private String message;

    private int status;

    private String path;

    public CustomErrorType(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public CustomErrorType() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
