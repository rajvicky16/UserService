package com.ecommerce.userservice.dtos.tokenDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private Boolean isValid;
    private String message;
}
