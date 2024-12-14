package com.ecommerce.userservice.exceptions;

public class UserAlreadyFound extends Exception{
    String email;

    public UserAlreadyFound(String email, String message){
        super(message);
        this.email = email;
    }
}
