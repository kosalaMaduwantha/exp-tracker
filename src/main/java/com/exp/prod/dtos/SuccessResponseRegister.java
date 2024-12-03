package com.exp.prod.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponseRegister {
    @NotEmpty(message = "message is required")
    private String message;
}
