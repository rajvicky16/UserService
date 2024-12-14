package com.ecommerce.userservice.dtos.userDtos;

import com.ecommerce.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;

    public User convertToUser() {
        User user = new User();

        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRoles(new ArrayList<>());

        return user;
    }
}
