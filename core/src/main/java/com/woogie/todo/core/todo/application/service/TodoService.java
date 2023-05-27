package com.woogie.todo.core.todo.application.service;

import com.woogie.todo.core.todo.application.usecase.CompleteTodo;
import com.woogie.todo.core.todo.application.usecase.CreateTodo;
import com.woogie.todo.core.todo.domain.Todo;
import com.woogie.todo.core.todo.domain.TodoRepository;
import com.woogie.todo.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService implements CreateTodo, CompleteTodo {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override

    public long create(CreateTodoCommand command) {
        var user = userRepository.findById(command.getUserId()).orElseThrow();

        var todo = new Todo(user, command.getTitle(), command.getDescription(), command.getStartTime(), command.getStartTime());

        todoRepository.save(todo);

        return todo.getId();
    }

    @Override
    public void complete(CompleteTodoCommand command) {
        var todo = todoRepository.findById(command.getId()).orElseThrow();

        todo.complete();

        todoRepository.update(todo);
    }
}
