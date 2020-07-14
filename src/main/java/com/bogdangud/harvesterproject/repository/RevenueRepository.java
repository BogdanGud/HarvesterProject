package com.bogdangud.harvesterproject.repository;

import com.bogdangud.harvesterproject.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    Revenue findById(long id);

    List<Revenue> findAll();

    @Query(value = "select * from revenue r where year(date) = :year", nativeQuery = true)
    List<Revenue> findAllByYear(@Param("year") int year);
}
