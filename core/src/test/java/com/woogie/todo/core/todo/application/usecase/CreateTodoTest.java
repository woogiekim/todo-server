package com.woogie.todo.core.todo.application.usecase;

import com.woogie.todo.core.support.BaseUseCaseTest;
import com.woogie.todo.core.todo.application.usecase.CreateTodo.CreateTodoCommand;
import com.woogie.todo.core.todo.domain.TodoRepository;
import com.woogie.todo.core.todo.domain.TodoStatus;
import com.woogie.todo.core.user.domain.User;
import com.woogie.todo.core.user.domain.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

import static com.woogie.todo.core.todo.application.usecase.CreateTodo.TITLE_LENGTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CreateTodoTest extends BaseUseCaseTest {

    @Autowired
    private CreateTodo createTodo;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 투두생성_성공() {
        var user = new User("wook@naver.com", "김태욱", "1234");
        userRepository.save(user);

        var now = OffsetDateTime.now();
        var command = new CreateTodoCommand(user.getId(), "title", "description", now, now);

        var todoId = createTodo.create(command);

        var foundTodo = todoRepository.findById(todoId).orElseThrow();

        assertThat(foundTodo.getId()).isNotNull();
        assertThat(foundTodo.getUser()).isEqualTo(user);
        assertThat(foundTodo.getTitle()).isEqualTo("title");
        assertThat(foundTodo.getDescription()).isEqualTo("description");
        assertThat(foundTodo.getStartTime()).isEqualToIgnoringSeconds(now);
        assertThat(foundTodo.getEndTime()).isEqualToIgnoringSeconds(now);
        assertThat(foundTodo.getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);
    }

    @Test
    void 투두생성_실패__유저아이디기_널인_경우() {
        var now = OffsetDateTime.now();

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateTodoCommand(null, "title", "description", now, now));
    }

    @Test
    void 투두생성_실패__타이틀의_길이가_긴_경우() {
        var title = "a".repeat(TITLE_LENGTH + 1);

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateTodoCommand(1L, title, "description", OffsetDateTime.now(), OffsetDateTime.now()));
    }

    @Test
    void 투두생성_실패__시작시간이_널인_경우() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateTodoCommand(1L, "title", "description", null, OffsetDateTime.now()));
    }

    @Test
    void 투두생성_실패__종료시간이_널인_경우() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateTodoCommand(1L, "title", "description", OffsetDateTime.now(), null));
    }
}
