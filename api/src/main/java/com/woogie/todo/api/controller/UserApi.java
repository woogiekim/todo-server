package com.woogie.todo.api.controller;

import com.woogie.todo.api.controller.dto.CreateUserReq;
import com.woogie.todo.core.user.application.usecase.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {

    private final CreateUser createUser;

    @PostMapping
    public void create(@RequestBody CreateUserReq req) {
        createUser.create(req.toCommand());
    }
}
