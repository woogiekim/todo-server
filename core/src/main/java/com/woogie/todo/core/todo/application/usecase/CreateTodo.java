package com.woogie.todo.core.todo.application.usecase;

import com.woogie.todo.core.support.SelfValidating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.OffsetDateTime;

public interface CreateTodo {
    int TITLE_LENGTH = 300;

    long create(CreateTodoCommand command);

    @Getter
    class CreateTodoCommand extends SelfValidating {

        @NotNull
        private final Long userId;

        @Size(max = TITLE_LENGTH)
        private final String title;

        private final String description;

        @NotNull
        private final OffsetDateTime startTime;

        @NotNull
        private final OffsetDateTime endTime;

        public CreateTodoCommand(Long userId, String title, String description, OffsetDateTime startTime, OffsetDateTime endTime) {
            this.userId = userId;
            this.title = title;
            this.description = description;
            this.startTime = startTime;
            this.endTime = endTime;

            this.validate();
        }
    }
}
