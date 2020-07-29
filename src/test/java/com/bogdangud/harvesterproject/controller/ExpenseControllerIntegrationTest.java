package com.bogdangud.harvesterproject.controller;

import com.bogdangud.harvesterproject.model.Expense;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static com.bogdangud.harvesterproject.utils.ExpenseUtils.createNewExpense;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Tag("integration-test")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ExpenseControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    void testAddingNewExpense() {

        Expense testExpense = createNewExpense();
        ResponseEntity<Expense> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/harvester/expense", testExpense, Expense.class);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getStatusCodeValue(), is(HttpStatus.CREATED.value()));

    }


    @Test
    void getAllExpensesTest() {

        ResponseEntity<Expense[]> expenseResponseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/harvester/expense", Expense[].class);

        assertAll(
                () -> assertThat(expenseResponseEntity, notNullValue()),
                () -> assertThat(expenseResponseEntity.getStatusCodeValue(), is(HttpStatus.OK.value())),
                () -> assertThat(expenseResponseEntity.getBody(), notNullValue())
        );
    }
}