package com.project.fin.exceptions;

public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
