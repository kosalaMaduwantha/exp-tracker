package com.exp.prod.expenseManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.prod.common.dtos.intermetiate.ExpenseCategoryTDto;
import com.exp.prod.common.dtos.request_dtos.ExpenseCategoryDto;
import com.exp.prod.expenseManagement.models.ExpenseCategories;
import com.exp.prod.expenseManagement.repositories.ExpenseCategoryRepository;
import com.exp.prod.userManagement.repositories.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private UserRepository userRepository;

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
    
}
