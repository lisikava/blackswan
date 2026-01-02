package com.project.fin.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
