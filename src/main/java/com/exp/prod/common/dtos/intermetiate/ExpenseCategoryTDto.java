package com.exp.prod.common.dtos.intermetiate;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseCategoryTDto {
    private String categoryId;
    private String categoryName;
    private String userName;
}
