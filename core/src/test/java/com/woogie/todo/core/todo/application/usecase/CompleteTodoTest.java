package com.woogie.todo.core.todo.application.usecase;

import com.woogie.todo.core.support.BaseUseCaseTest;
import com.woogie.todo.core.todo.application.usecase.CompleteTodo.CompleteTodoCommand;
import com.woogie.todo.core.todo.domain.Todo;
import com.woogie.todo.core.todo.domain.TodoRepository;
import com.woogie.todo.core.todo.domain.TodoStatus;
import com.woogie.todo.core.user.domain.User;
import com.woogie.todo.core.user.domain.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CompleteTodoTest extends BaseUseCaseTest {

    @Autowired
    private CompleteTodo completeTodo;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 투두완료_성공() {
        //given
        var user = new User("wook@naver.com", "김태욱", "1234");
        userRepository.save(user);

        var now = OffsetDateTime.now();
        var todo = new Todo(user, "title", "description", now, now);
        todoRepository.save(todo);

        assertThat(todo.getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);

        //when
        var command = new CompleteTodoCommand(todo.getId());

        completeTodo.complete(command);

        //then
        var foundTodo = todoRepository.findById(todo.getId()).orElseThrow();

        assertThat(foundTodo.getStatus()).isEqualTo(TodoStatus.COMPLETED);
    }

    @Test
    void 투두완료_실패__투두아이디가_널인_경우() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CompleteTodoCommand(null));
    }
}