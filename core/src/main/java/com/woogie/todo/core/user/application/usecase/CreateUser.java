package com.woogie.todo.core.user.application.usecase;

import com.woogie.todo.core.support.SelfValidating;
import com.woogie.todo.core.user.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface CreateUser {

    void create(CreateUserCommand command);

    class CreateUserCommand extends SelfValidating {

        @Email
        private final String email;

        @NotBlank
        private final String name;

        @NotBlank
        private final String password;

        public CreateUserCommand(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;

            this.validate();
        }

        public User toEntity() {
            return new User(email, name, password);
        }
    }
}
