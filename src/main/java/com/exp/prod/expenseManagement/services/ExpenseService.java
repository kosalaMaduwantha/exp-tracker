package com.exp.prod.expenseManagement.services;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.print.DocFlavor.STRING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.prod.common.dtos.intermetiate.ExpenseCategoryTDto;
import com.exp.prod.common.dtos.request_dtos.ExpenseCategoryDto;
import com.exp.prod.common.dtos.request_dtos.ExpenseDto;
import com.exp.prod.common.exceptions.Exceptions.CategoryNotFoundException;
import com.exp.prod.common.exceptions.Exceptions.UserNotFoundException;
import com.exp.prod.expenseManagement.models.Expense;
import com.exp.prod.expenseManagement.models.ExpenseCategories;
import com.exp.prod.expenseManagement.repositories.ExpenseCategoryRepository;
import com.exp.prod.expenseManagement.repositories.ExpenseRepository;
import com.exp.prod.userManagement.models.User;
import com.exp.prod.userManagement.repositories.UserRepository;
import com.exp.prod.userManagement.services.UserManagementService;

import jakarta.validation.Valid;

@Service
public class ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public boolean addExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        try{
            ExpenseCategories expenseCategory = new ExpenseCategories(
                expenseCategoryDto.getCategoryName()
            );
            this.expenseCategoryRepository.save(expenseCategory);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean createExpense(ExpenseDto expenseDto, String username) {
        try{
            User user = this.userRepository.findByUserName(username);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            }
            ExpenseCategories expenseCategory = this.expenseCategoryRepository.findByCategoryName(expenseDto.getCategoryName());
            if (expenseCategory == null) {
                throw new CategoryNotFoundException("Expense category not found");
            }
            Expense expense = new Expense(
                user,
                expenseCategory,
                expenseDto.getAmount(),
                expenseDto.getCurrency(),
                LocalDateTime.now(),
                expenseDto.getNotes()
            );
            this.expenseRepository.save(expense);
            return true;
        } catch (Exception e) {
            logger.error("Error creating expense: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
}
