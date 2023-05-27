package com.woogie.todo.api.controller;

import com.woogie.todo.api.controller.dto.CreateTodoReq;
import com.woogie.todo.core.todo.application.usecase.CreateTodo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoApi {

    private final CreateTodo createTodo;

    @PostMapping
    public void create(@RequestBody CreateTodoReq req) {
        createTodo.create(req.toCommand());
    }
}
