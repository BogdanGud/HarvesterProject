package com.bogdangud.harvesterproject.controller;

import com.bogdangud.harvesterproject.model.Revenue;
import com.bogdangud.harvesterproject.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/revenue")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RevenueController {
    private final RevenueService revenueService;

    @GetMapping
    public List<Revenue> getAllRevenues() {
        return revenueService.getAllRevenues();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Revenue revenue) {
        revenueService.addNewRevenue(revenue);
        return ResponseEntity.status(HttpStatus.CREATED).body(" New revenue added successfully");
    }

    @GetMapping("/year/{year}")
    public List<Revenue> getByYear(@PathVariable("year") int year){
        return revenueService.getByYear(year);
    }
}
