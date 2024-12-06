package com.exp.prod.expenseManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exp.prod.expenseManagement.models.ExpenseCategories;
import com.exp.prod.userManagement.models.User;

@Repository
public interface ExpenseCategoryRepository extends CrudRepository<ExpenseCategories, String> {
    ExpenseCategories findByCategoryName(String categoryName);
}
