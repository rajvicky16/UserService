package com.ecommerce.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "tokens")
public class Token extends BaseModel{
    private String tokenVal;
    @ManyToOne
    private User user;
    private Date expiryDate;
}

/*
      1   -   M
    User --> Token: One user can have multiple tokens, but a token can only belong to one user.
      1   -   1
*/
