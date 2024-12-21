package com.ecommerce.userservice.security.models;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class CustomGrantedAuthority implements GrantedAuthority, Serializable {
    private String authority;

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}