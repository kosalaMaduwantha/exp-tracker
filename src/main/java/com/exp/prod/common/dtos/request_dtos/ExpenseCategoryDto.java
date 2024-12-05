package com.exp.prod.common.dtos.request_dtos;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseCategoryDto {
    private String categoryName;
}
