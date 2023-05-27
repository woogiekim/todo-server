package com.woogie.todo.api.controller.dto;

import com.woogie.todo.core.user.application.usecase.CreateUser.CreateUserCommand;

public record CreateUserReq(
        String email,
        String name,
        String password
) {
    public CreateUserCommand toCommand() {
        return new CreateUserCommand(this.email, this.name, this.password);
    }
}
