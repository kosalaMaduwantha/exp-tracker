package com.exp.prod.expenseManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exp.prod.expenseManagement.models.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, String> {
       
}
