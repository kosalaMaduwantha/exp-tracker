package com.exp.prod.common.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponseLogin {
    @NotEmpty(message = "token is required")
    private String token;
}
