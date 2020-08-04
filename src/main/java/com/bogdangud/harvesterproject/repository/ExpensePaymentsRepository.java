package com.bogdangud.harvesterproject.repository;

import com.bogdangud.harvesterproject.model.DebtPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensePaymentsRepository extends JpaRepository<DebtPayment, Long> {

    @Query(value = "SELECT * from expense_payments where expense_id = :id and is_paid = false", nativeQuery = true)
    List<DebtPayment> findAllUnpaidDebtsForExpense(@Param("id") long id);

    @Query(value = "SELECT e from DebtPayment e where e.debtorId = :id")
    List<DebtPayment> findAllExpenseForUser(@Param("id") long userId);
}
