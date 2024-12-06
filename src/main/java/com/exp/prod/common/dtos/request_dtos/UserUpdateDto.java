package com.exp.prod.common.dtos.request_dtos;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @NotEmpty(message = "userName is required")
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private int phoneNumber;
}
