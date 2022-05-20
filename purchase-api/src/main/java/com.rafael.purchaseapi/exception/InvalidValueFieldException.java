package com.rafael.purchaseapi.exception;

public class InvalidValueFieldException extends RuntimeException {
    public InvalidValueFieldException(String message) {
        super(message + " - Invalid or Missing parameter");
    }
}
