package com.woogie.todo.core.todo.domain;

import com.woogie.todo.core.user.domain.User;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.*;

class TodoTest {

    @Test
    void 투두생성_성공() {
        var user = new User("wook@naver.com", "김태욱", "1234");
        var now = OffsetDateTime.now();

        var todo = new Todo(user, "title", "description", now, now);

        assertThat(todo.getId()).isNull();
        assertThat(todo.getUser()).isSameAs(user);
        assertThat(todo.getTitle()).isEqualTo("title");
        assertThat(todo.getDescription()).isEqualTo("description");
        assertThat(todo.getStartTime()).isBeforeOrEqualTo(todo.getEndTime());
        assertThat(todo.getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);
        assertThat(todo.getCreatedAt()).isBeforeOrEqualTo(OffsetDateTime.now());
    }

    @Test
    void 투두생성_성공__시작시간이_종료시간보다_이전() {
        var user = new User("wook@naver.com", "김태욱", "1234");
        var now = OffsetDateTime.now();

        assertThatNoException()
                .isThrownBy(() -> new Todo(user, "title", "description", now, now.plusSeconds(1)));
    }

    @Test
    void 투두생성_실패__유저가_널인_경우() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Todo(null, "title", "description", OffsetDateTime.now(), OffsetDateTime.now().plusSeconds(1)));
    }

    @Test
    void 투두생성_실패__시작시간이_널인_경우() {
        var user = new User("wook@naver.com", "김태욱", "1234");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Todo(user, "title", "description", null, OffsetDateTime.now().plusSeconds(1)));
    }

    @Test
    void 투두생성_실패__종료시간이_널인_경우() {
        var user = new User("wook@naver.com", "김태욱", "1234");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Todo(user, "title", "description", OffsetDateTime.now(), null));
    }

    @Test
    void 투두생성_실패__종료시간이_시작시간_이전인_경우() {
        var user = new User("wook@naver.com", "김태욱", "1234");
        var now = OffsetDateTime.now();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Todo(user, "title", "description", now, now.minusSeconds(1)));
    }

    @Test
    void 투두완료_성공() {
        var user = new User("wook@naver.com", "김태욱", "1234");
        var now = OffsetDateTime.now();

        var todo = new Todo(user, "title", "description", now, now);

        assertThat(todo.getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);

        todo.complete();

        assertThat(todo.getStatus()).isEqualTo(TodoStatus.COMPLETED);
    }

    @Test
    void 투두완료_실패__이미_완료된_경우() {
        var user = new User("wook@naver.com", "김태욱", "1234");
        var now = OffsetDateTime.now();

        var todo = new Todo(user, "title", "description", now, now);

        todo.complete();

        assertThatIllegalStateException()
                .isThrownBy(todo::complete);
    }
}
