package com.ecommerce.userservice.dtos.userDtos;

import com.ecommerce.userservice.dtos.roleDtos.RoleDto;
import com.ecommerce.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpResponseDto {
    private String userName;
    private String email;
    private List<RoleDto> roleDtoList;

    public static SignUpResponseDto createFromUser(User user) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

        signUpResponseDto.setUserName(user.getName());
        signUpResponseDto.setEmail(user.getEmail());
        signUpResponseDto.setRoleDtoList(RoleDto.createFromRoleList(user.getRoles()));

        return signUpResponseDto;
    }
}
