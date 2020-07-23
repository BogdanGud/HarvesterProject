package com.bogdangud.harvesterproject.repository;

import com.bogdangud.harvesterproject.model.ExpensePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensePaymentsRepository extends JpaRepository<ExpensePayment, Long> {

    @Query(value = "SELECT * from expense_payments where expense_id = :id and is_paid = false", nativeQuery = true)
    List<ExpensePayment> findAllUnpaidDebts(@Param("id") long id);
}
