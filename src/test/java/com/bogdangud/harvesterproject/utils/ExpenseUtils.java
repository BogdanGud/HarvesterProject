package com.bogdangud.harvesterproject.utils;

import com.bogdangud.harvesterproject.model.Expense;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ExpenseUtils {
    public static Expense createNewExpense() {
        return Expense.builder()
                .amount(999)
                .date(new GregorianCalendar(2020, Calendar.JULY, 5).getTime())
                .description("integration test")
                .paidByUserId(1)
                .paid(false)
                .build();
    }
}
