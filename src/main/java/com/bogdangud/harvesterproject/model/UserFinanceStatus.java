package com.bogdangud.harvesterproject.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserFinanceStatus {

    private User user;
    private double totalRevenue;
    private double totalExpense;
    private double totalBalance;
    private double debt;
    private List<DebtPayment> userExpensesPayments;

}
