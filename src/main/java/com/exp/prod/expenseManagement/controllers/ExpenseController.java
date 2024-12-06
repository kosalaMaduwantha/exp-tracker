package com.exp.prod.expenseManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exp.prod.common.dtos.intermetiate.ExpenseCategoryTDto;
import com.exp.prod.common.dtos.request_dtos.ExpenseCategoryDto;
import com.exp.prod.common.dtos.request_dtos.ExpenseDto;
import com.exp.prod.expenseManagement.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add_expense_category")
    public ResponseEntity<String> addExpenseCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        /*
         * This method adds an expense category
         */
        try{
            if (this.expenseService.addExpenseCategory(expenseCategoryDto)) {
                return new ResponseEntity<>(
                    "Expense category added successfully", 
                    HttpStatus.CREATED);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    @PostMapping("/create_expense")
    public ResponseEntity<String> createExpense(@Valid @RequestBody ExpenseDto expenseDto) {
        /*
         * This method creates an expense
         */
        try{
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (this.expenseService.createExpense(expenseDto, userDetails.getUsername())) {
                return new ResponseEntity<>(
                    "Expense created successfully", 
                    HttpStatus.CREATED);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
