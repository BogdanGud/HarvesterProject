package com.bogdangud.harvesterproject.service;

import com.bogdangud.harvesterproject.model.Expense;
import com.bogdangud.harvesterproject.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public Expense getById(long id) {
        return expenseRepository.findById(id);
    }

    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }
}
