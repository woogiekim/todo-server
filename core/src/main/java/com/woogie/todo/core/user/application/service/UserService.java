package com.woogie.todo.core.user.application.service;

import com.woogie.todo.core.user.domain.UserRepository;
import com.woogie.todo.core.user.application.usecase.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements CreateUser {

    private final UserRepository userRepository;

    @Override
    public void create(CreateUserCommand command) {
        userRepository.save(command.toEntity());
    }
}
