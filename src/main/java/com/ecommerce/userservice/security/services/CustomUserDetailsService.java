package com.ecommerce.userservice.security.services;

import com.ecommerce.userservice.models.Role;
import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.repositories.UserRepository;
import com.ecommerce.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found for email: " + email);
        }

        User currUser = optionalUser.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(currUser.getName())
                .password(currUser.getPassword())
                .roles(currUser.getRoles().stream().map(Role::getRoleValue).toArray(String[]::new))
                .build();
    }
}
