package com.woogie.todo.api.controller.dto;

import com.woogie.todo.core.todo.application.usecase.CreateTodo.CreateTodoCommand;

import java.time.OffsetDateTime;

public record CreateTodoReq(
        Long userId,
        String title,
        String description,
        OffsetDateTime startTime,
        OffsetDateTime endTime
) {

    public CreateTodoCommand toCommand() {
        return new CreateTodoCommand(this.userId, this.title, this.description, this.startTime, this.endTime);
    }
}
