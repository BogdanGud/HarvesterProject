package com.bogdangud.harvesterproject.repository;

import com.bogdangud.harvesterproject.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findById(long id);

    List<Expense> findAll();

}
