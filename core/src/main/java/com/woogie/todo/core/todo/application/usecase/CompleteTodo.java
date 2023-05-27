package com.woogie.todo.core.todo.application.usecase;

import com.woogie.todo.core.support.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public interface CompleteTodo {
    void complete(CompleteTodoCommand command);

    @Getter
    class CompleteTodoCommand extends SelfValidating {

        @NotNull
        private final Long id;

        public CompleteTodoCommand(Long id) {
            this.id = id;

            this.validate();
        }
    }
}
