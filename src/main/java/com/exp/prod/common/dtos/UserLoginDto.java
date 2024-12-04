package com.exp.prod.common.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotEmpty(message = "userName is required")
    private String userName;
    @NotEmpty(message = "password is required")
    private String password;
}
