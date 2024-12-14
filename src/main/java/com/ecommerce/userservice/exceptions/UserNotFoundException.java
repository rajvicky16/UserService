package com.ecommerce.userservice.exceptions;

public class UserNotFoundException extends Exception {
    String email;

    public UserNotFoundException(String email, String message) {
        super(message);
        this.email = email;
    }
}
