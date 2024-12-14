package com.ecommerce.userservice.services;

import com.ecommerce.userservice.exceptions.InvalidPasswordException;
import com.ecommerce.userservice.exceptions.UserAlreadyFound;
import com.ecommerce.userservice.exceptions.UserNotFoundException;
import com.ecommerce.userservice.models.Token;
import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.repositories.TokenRepository;
import com.ecommerce.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service("selfUserServiceImpl")
public class SelfUserServiceImpl implements UserService{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    public SelfUserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                               TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUpUser(User user) throws UserAlreadyFound {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()){
            throw new UserAlreadyFound(user.getEmail(), "User already found with this email, please login or use another email");
        }
        user.validateMandatoryFields();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Token loginUser(User user) throws UserNotFoundException, InvalidPasswordException {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException(user.getEmail(), "User not found with this email, please sign up or use another email");
        }

        boolean isPasswordMatching = bCryptPasswordEncoder.matches(user.getPassword(), optionalUser.get().getPassword());
        if(!isPasswordMatching){
            throw new InvalidPasswordException(user.getEmail(), "Password is incorrect, please try again");
        }

        Token token = new Token();
        token.setTokenVal(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(optionalUser.get());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        token.setExpiryDate(calendar.getTime());

        return tokenRepository.save(token);
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public void logoutUser(String token) {

    }
}
