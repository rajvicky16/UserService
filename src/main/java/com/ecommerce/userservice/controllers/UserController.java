package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.dtos.tokenDtos.ValidateTokenResponseDto;
import com.ecommerce.userservice.dtos.userDtos.LoginRequestDto;
import com.ecommerce.userservice.dtos.userDtos.LoginResponseDto;
import com.ecommerce.userservice.dtos.userDtos.SignUpRequestDto;
import com.ecommerce.userservice.dtos.userDtos.SignUpResponseDto;
import com.ecommerce.userservice.exceptions.InvalidPasswordException;
import com.ecommerce.userservice.exceptions.UserAlreadyFound;
import com.ecommerce.userservice.exceptions.UserNotFoundException;
import com.ecommerce.userservice.models.Token;
import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUpUser(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyFound {
        User user = userService.signUpUser(signUpRequestDto.convertToUser());

        return new ResponseEntity<>(
            SignUpResponseDto.createFromUser(user),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidPasswordException {
        Token token = userService.loginUser(loginRequestDto.convertToUser());

        return new ResponseEntity<>(
                LoginResponseDto.createFromToken(token),
                HttpStatus.OK
        );
    }

    @GetMapping("/validateToken/{token}")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logoutUser(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
