package com.ecommerce.userservice.dtos.userDtos;

import com.ecommerce.userservice.dtos.roleDtos.RoleDto;
import com.ecommerce.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDto {
    private String name;
    private String email;
    private String token;
    private List<RoleDto> roleDtoList;

    public static LoginResponseDto createFromToken(Token token) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        loginResponseDto.setName(token.getUser().getName());
        loginResponseDto.setEmail(token.getUser().getEmail());
        loginResponseDto.setToken(token.getTokenVal());
        loginResponseDto.setRoleDtoList(RoleDto.createFromRoleList(token.getUser().getRoles()));

        return loginResponseDto;
    }
}
