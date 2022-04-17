package com.example.demo.exception;

public class UserException extends Exception {

    private String message;

    public UserException(String message) {
    }

    public String getMessage() {
        return message;
    }
}
