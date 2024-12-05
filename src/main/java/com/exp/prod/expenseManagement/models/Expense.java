package com.exp.prod.expenseManagement.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id @Column(name = "expense_id") @GeneratedValue(strategy = GenerationType.AUTO)
    private int expenseId;

    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "expense_date")
    private String expenseDate;

    @Column(name = "created_at")
    private String notes;
}
