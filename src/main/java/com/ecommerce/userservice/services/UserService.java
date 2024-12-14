package com.ecommerce.userservice.services;

import com.ecommerce.userservice.exceptions.InvalidPasswordException;
import com.ecommerce.userservice.exceptions.UserAlreadyFound;
import com.ecommerce.userservice.exceptions.UserNotFoundException;
import com.ecommerce.userservice.models.Token;
import com.ecommerce.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signUpUser(User user) throws UserAlreadyFound;

    Token loginUser(User user) throws UserNotFoundException, InvalidPasswordException;

    boolean validateToken(String token);

    void logoutUser(String token);
}
