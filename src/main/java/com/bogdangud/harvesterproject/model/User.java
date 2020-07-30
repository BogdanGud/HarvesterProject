package com.bogdangud.harvesterproject.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_driver")
    private boolean isDriver;

    @Column(name = "is_investor")
    private boolean isInvestor;

    @Column(name = "is_assistant")
    private boolean isAssistant;

}
