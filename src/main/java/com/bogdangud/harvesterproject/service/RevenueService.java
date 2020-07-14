package com.bogdangud.harvesterproject.service;

import com.bogdangud.harvesterproject.model.Revenue;
import com.bogdangud.harvesterproject.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RevenueService {
    private final RevenueRepository revenueRepository;

    public Revenue getById(long id) {
        return revenueRepository.findById(id);
    }

    public List<Revenue> getAllRevenues() {
        return revenueRepository.findAll();
    }

    public void addNewRevenue(Revenue newRevenue) {
        revenueRepository.save(newRevenue);
    }

    public List<Revenue> getByYear(int year) {
        return revenueRepository.findAllByYear(year);
    }


}
