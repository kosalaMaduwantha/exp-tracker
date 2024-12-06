package com.exp.prod.common.dtos.response_dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
    @NotEmpty(message = "message is required")
    private String message;
}
