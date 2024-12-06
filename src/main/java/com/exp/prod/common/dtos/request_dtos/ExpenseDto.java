package com.exp.prod.common.dtos.request_dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    @NotEmpty(message = "User id is required")
    private String categoryName;
    private double amount;
    private String currency;
    private String notes;
}
