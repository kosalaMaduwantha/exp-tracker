package com.exp.prod.common.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty(message = "userName is required")
    private String userName;
    @NotEmpty(message = "email is required")
    private String email;
    @NotEmpty(message = "password is required")
    private String password; // hashed password
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String salt;
    private String tempPassword;
}
