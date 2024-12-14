package com.ecommerce.userservice.dtos.roleDtos;

import com.ecommerce.userservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleDto {
    private String roleValue;

    public static List<RoleDto> createFromRoleList(List<Role> roles) {
        if(roles == null || roles.isEmpty()) return new ArrayList<>();

        List<RoleDto> roleDtoList = new ArrayList<>();
        for(Role role : roles) {
            RoleDto roleDto = new RoleDto();
            roleDto.setRoleValue(role.getRoleValue());
            roleDtoList.add(roleDto);
        }

        return roleDtoList;
    }
}
