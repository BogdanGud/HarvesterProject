package com.bogdangud.harvesterproject.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "square")
    private double square;

    @Column(name = "price_per_hectare")
    private double pricePerHectare;

    @Column(name = "combiner_driver_payment_hectare")
    private double combineDriverPaymentPerHectare;

    @Column(name = "assistant_payment")
    private double assistantPayment;

    @Column(name = "comment")
    private String comment;
}
