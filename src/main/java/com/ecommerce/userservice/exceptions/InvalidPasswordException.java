package com.ecommerce.userservice.exceptions;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String email, String message){
        super("InvalidPasswordException: " + message + " for email: " + email);
    }
}
