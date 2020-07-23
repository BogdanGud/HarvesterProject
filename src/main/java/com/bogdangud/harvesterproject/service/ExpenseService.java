package com.bogdangud.harvesterproject.service;

import com.bogdangud.harvesterproject.model.Expense;
import com.bogdangud.harvesterproject.model.ExpensePayment;
import com.bogdangud.harvesterproject.model.User;
import com.bogdangud.harvesterproject.repository.ExpensePaymentsRepository;
import com.bogdangud.harvesterproject.repository.ExpenseRepository;
import com.bogdangud.harvesterproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpensePaymentsRepository expensePaymentsRepository;
    private final UserRepository userRepository;
    Logger log = LoggerFactory.getLogger(ExpenseService.class);

    public Expense getById(long id) {
        return expenseRepository.findById(id);
    }

    public void addNewExpense(Expense expense) {
        Expense tempExp = expenseRepository.save(expense);
        log.info("New expense added successfully: " + tempExp.toString());
        createNewExpensePayments(tempExp);
    }

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    private void createNewExpensePayments(Expense expense) {
        double sumPerUser = expense.getAmount() / getNumberOfInvestors();
        List<User> debtors = userRepository.findAllByIdIsNot(expense.getPaidByUserId());
        List<ExpensePayment> payments = debtors.stream()
                .map(d -> ExpensePayment.builder()
                        .expenseId(expense.getId())
                        .amount(sumPerUser)
                        .debtorId(d.getId())
                        .isPaid(false)
                        .build())
                .collect(Collectors.toList());

        expensePaymentsRepository.saveAll(payments);
        log.info("Expenses for debtors added successfully: " + payments.toString());
    }

    private int getNumberOfInvestors() {
        return userRepository.findAll().size();
    }

    public void payOffDebt(ExpensePayment payment) {
        expensePaymentsRepository.save(payment);
        if (isAllRelatedPaymentsDone(payment.getExpenseId())) {
            Expense tempExp = expenseRepository.getOne(payment.getExpenseId());
            tempExp.setPaid(true);
            expenseRepository.save(tempExp);
        }
    }

    private boolean isAllRelatedPaymentsDone(long id) {
        if (expensePaymentsRepository.findAllUnpaidDebts(id).isEmpty()) return true;
        return false;
    }


}
