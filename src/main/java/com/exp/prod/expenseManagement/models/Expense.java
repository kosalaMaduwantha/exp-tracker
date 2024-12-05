package com.exp.prod.expenseManagement.models;

import java.time.LocalDateTime;

import com.exp.prod.userManagement.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private ExpenseCategories category;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "expense_date")
    private String expenseDate;

    @Column(name = "created_at")
    private String notes;
}
