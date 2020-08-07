package com.bogdangud.harvesterproject.controller;

import com.bogdangud.harvesterproject.model.Expense;
import com.bogdangud.harvesterproject.model.DebtPayment;
import com.bogdangud.harvesterproject.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAll();
    }

    @PostMapping
    public ResponseEntity<String> addNewExpense(@RequestBody Expense expense) {
        expenseService.addNewExpense(expense);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @PutMapping("/payoff")
    public ResponseEntity<String> updatePayment(@RequestBody DebtPayment payment) {
        expenseService.payOffDebt(payment);
        return ResponseEntity
                .status(HttpStatus.OK).build();
    }
}
