package com.bogdangud.harvesterproject.controller;

import com.bogdangud.harvesterproject.model.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Tag("integration-test")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)

public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllUsers() {
        ResponseEntity<User[]> usersResponseEntity = restTemplate.getForEntity(
                "/api/user", User[].class);

        assertThat(usersResponseEntity, notNullValue());
        assertThat(usersResponseEntity.getStatusCodeValue(), is(HttpStatus.OK.value()));

    }

    @Test
    void testGetUser() {
        ResponseEntity<User> usersResponseEntity = restTemplate.getForEntity(
                "/api/user/1", User.class);

        assertThat(usersResponseEntity, notNullValue());
        assertThat(usersResponseEntity.getStatusCodeValue(), is(HttpStatus.OK.value()));

    }
}