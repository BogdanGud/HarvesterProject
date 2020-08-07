package com.bogdangud.harvesterproject.controller;

import com.bogdangud.harvesterproject.model.User;
import com.bogdangud.harvesterproject.model.UserFinanceStatus;
import com.bogdangud.harvesterproject.repository.UserRepository;
import com.bogdangud.harvesterproject.service.UserFinanceStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository userRepository;
    private final UserFinanceStatusService financeStatusService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userRepository.getOne(Long.parseLong(id));
    }

    @GetMapping("/status/{id}")
    public UserFinanceStatus getUserFinanceStatus(@PathVariable String id) {
        return financeStatusService.getUserFinanceStatus(Long.parseLong(id), 2020);
    }


}
