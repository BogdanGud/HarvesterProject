package com.bogdangud.harvesterproject.service;

import com.bogdangud.harvesterproject.model.DebtPayment;
import com.bogdangud.harvesterproject.model.Revenue;
import com.bogdangud.harvesterproject.model.User;
import com.bogdangud.harvesterproject.model.UserFinanceStatus;
import com.bogdangud.harvesterproject.repository.ExpensePaymentsRepository;
import com.bogdangud.harvesterproject.repository.RevenueRepository;
import com.bogdangud.harvesterproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNullElse;

@Component
@RequiredArgsConstructor
public class UserFinanceStatusService {

    private final RevenueRepository revenueRepository;
    private final UserRepository userRepository;
    private final ExpensePaymentsRepository paymentsRepository;
    private List<DebtPayment> userPayments;
    private List<Revenue> yearsRevenue;


    public UserFinanceStatus getUserFinanceStatus(long userId, int year) {
        User user = userRepository.getOne(userId);
        fillExpensePaymentsForUser(user.getId());
        fillYearsRevenue(year);
        return UserFinanceStatus.builder()
                .user(user)
                .userExpensesPayments(userPayments)
                .totalExpense(getExpenseForUser())
                .debt(getDebtForUser())
                .totalRevenue(getTotalRevenueForUser(user))
                .totalBalance(getTotalBalance(user))
                .build();

    }

    private double getTotalBalance(User user) {
        return getTotalRevenueForUser(user) - getExpenseForUser();
    }

    private double getExpenseForUser() {
        return userPayments.stream()
                .mapToDouble(DebtPayment::getAmount)
                .sum();
    }

    private double getDebtForUser() {
        return userPayments.stream()
                .filter(p -> !p.isPaid())
                .mapToDouble(DebtPayment::getAmount)
                .sum();
    }

    private void fillExpensePaymentsForUser(long userId) {
        userPayments = paymentsRepository.findAllExpenseForUser(userId);
    }

    private void fillYearsRevenue(int year) {
        yearsRevenue = revenueRepository.findAllByYear(requireNonNullElse(year, LocalDate.now().getYear()));
    }

    private double getTotalRevenueForUser(User user) {
        double userRevenue = 0;
        if (user.isAssistant()) userRevenue += getTotalRevenueForAssistant();
        if (user.isDriver()) userRevenue += getTotalRevenueForDriver();
        if (user.isInvestor()) userRevenue += getTotalRevenueForInvestor();
        return userRevenue;

    }

    private double getTotalRevenueForInvestor() {
        int numberOfInvestors = userRepository.getAmountOfInvestors();
        double allRevenue = yearsRevenue.stream()
                .mapToDouble(r -> r.getPricePerHectare() * r.getSquare())
                .sum();

        return (allRevenue - getTotalRevenueForDriver() - getTotalRevenueForAssistant()) / numberOfInvestors;

    }

    private double getTotalRevenueForDriver() {
        return yearsRevenue.stream()
                .mapToDouble(r -> r.getCombineDriverPaymentPerHectare() * r.getSquare())
                .sum();
    }

    private double getTotalRevenueForAssistant() {
        return yearsRevenue.stream()
                .mapToDouble(r -> r.getAssistantPayment() * r.getPricePerHectare() * r.getSquare())
                .sum();
    }
}
