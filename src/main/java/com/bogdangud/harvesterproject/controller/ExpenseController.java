package com.bogdangud.harvesterproject.controller;

import com.bogdangud.harvesterproject.model.Expense;
import com.bogdangud.harvesterproject.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAll();
    }

    @PostMapping
    public ResponseEntity addNewExpense(@RequestBody Expense expense) {
        expenseService.save(expense);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(" New expense added successfully");
    }
}
