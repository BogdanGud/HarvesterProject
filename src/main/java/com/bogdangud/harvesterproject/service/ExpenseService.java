package com.bogdangud.harvesterproject.service;

import com.bogdangud.harvesterproject.model.DebtPayment;
import com.bogdangud.harvesterproject.model.Expense;
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
        List<User> debtors = userRepository.findAllDebtors(expense.getPaidByUserId());
        List<DebtPayment> payments = debtors.stream()
                .map(d -> DebtPayment.builder()
                        .expenseId(expense.getId())
                        .amount(sumPerUser)
                        .debtorId(d.getId())
                        .paid(false)
                        .build())
                .collect(Collectors.toList());

        expensePaymentsRepository.saveAll(payments);
        log.info("Expenses for debtors added successfully: " + payments.toString());
    }

    private int getNumberOfInvestors() {
        return userRepository.getAmountOfInvestors();
    }

    public void payOffDebt(DebtPayment payment) {
        expensePaymentsRepository.save(payment);
        if (isAllRelatedPaymentsDone(payment.getExpenseId())) {
            Expense tempExp = expenseRepository.getOne(payment.getExpenseId());
            tempExp.setPaid(true);
            expenseRepository.save(tempExp);
        }
    }

    private boolean isAllRelatedPaymentsDone(long id) {
        return expensePaymentsRepository.findAllUnpaidDebtsForExpense(id).isEmpty();
    }


}
