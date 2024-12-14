package com.ecommerce.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles;

    public void validateMandatoryFields() {
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new IllegalArgumentException("Email is mandatory");
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new IllegalArgumentException("Password is mandatory");
        }
    }
}
