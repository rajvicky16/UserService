package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.dtos.tokenDtos.ValidateTokenRequestDto;
import com.ecommerce.userservice.dtos.tokenDtos.ValidateTokenResponseDto;
import com.ecommerce.userservice.dtos.userDtos.*;
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
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@PathVariable(name = "token") String token, @RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        boolean isValid = userService.validateToken(token, validateTokenRequestDto.getUserId());
        ValidateTokenResponseDto validateTokenResponseDto = new ValidateTokenResponseDto();
        if(isValid){
            validateTokenResponseDto.setIsValid(true);
            validateTokenResponseDto.setMessage("Token is valid");
            return new ResponseEntity<>(
                    validateTokenResponseDto,
                    HttpStatus.OK
            );
        }
        validateTokenResponseDto.setIsValid(false);
        validateTokenResponseDto.setMessage("Token is invalid");
        return new ResponseEntity<>(
                validateTokenResponseDto,
                HttpStatus.UNAUTHORIZED
        );
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody LogoutRequestDto logoutRequestDto){
        userService.logoutUser(logoutRequestDto.getToken(), logoutRequestDto.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
