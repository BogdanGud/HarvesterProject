package com.bogdangud.harvesterproject.repository;

import com.bogdangud.harvesterproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findById(long id);

    List<User> findAllByIdIsNot(long id);

    @Query(value = "select u from User u where u.id <> :id and u.isInvestor = true ")
    List<User> findAllDebtors(@Param("id") long userId);


    @Query(value = "select count (u) from User u where u.isInvestor = true")
    int getAmountOfInvestors();

}
