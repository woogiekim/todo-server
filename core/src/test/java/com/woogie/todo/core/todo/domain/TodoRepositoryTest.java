package com.woogie.todo.core.todo.domain;

import com.woogie.todo.core.support.BaseRepositoryTest;
import com.woogie.todo.core.user.domain.User;
import com.woogie.todo.core.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TodoRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 투두생성() {
        //given
        var user = new User("wook@naver.com", "김태욱", "1234");
        userRepository.save(user);

        var todo = new Todo(user, "title", "description", OffsetDateTime.now(), OffsetDateTime.now());

        //when
        todoRepository.save(todo);

        //then
        var foundTodo = todoRepository.findById(todo.getId()).orElseThrow();

        assertThat(foundTodo.getId()).isNotNull();
        assertThat(foundTodo.getUser()).isEqualTo(user);
        assertThat(foundTodo.getTitle()).isEqualTo("title");
        assertThat(foundTodo.getDescription()).isEqualTo("description");
        assertThat(foundTodo.getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);
        assertThat(foundTodo.getCreatedAt()).isNotNull();
    }
}