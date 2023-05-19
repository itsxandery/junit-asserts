package com.example.junitasserts;

public class InsufficientInventoryException extends Exception {
    
    public InsufficientInventoryException(String errorMessage) {
        super(errorMessage);
    }

}
