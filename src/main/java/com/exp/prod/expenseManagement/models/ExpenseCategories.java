package com.exp.prod.expenseManagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense_categories")
public class ExpenseCategories {
    @Id @Column(name = "category_id") @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    @Column(name = "category_name")
    private String categoryName;

    public ExpenseCategories(String categoryName) {
        this.categoryName = categoryName;
    }
}//
