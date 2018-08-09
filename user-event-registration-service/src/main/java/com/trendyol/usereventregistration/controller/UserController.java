package com.trendyol.usereventregistration.controller;


import com.trendyol.usereventregistration.model.RegisteredUsersGetResponse;
import com.trendyol.usereventregistration.model.UserRegistrationPostRequest;
import com.trendyol.usereventregistration.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Validated UserRegistrationPostRequest request) {
        service.registerUser(request);
    }

    @GetMapping
    public List<RegisteredUsersGetResponse> getRegisteredUsers() {
        return service.getRegisteredUsers();
    }
}
