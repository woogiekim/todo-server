package com.woogie.todo.core.user.domain;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class UserTest {

    @Test
    void 유저생성_성공() {
        var user = new User("wook@naver.com", "김태욱", "1234");

        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("wook@naver.com");
        assertThat(user.getName()).isEqualTo("김태욱");
        assertThat(user.getPassword()).isEqualTo("1234");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVATED);
        assertThat(user.getCreatedAt()).isBeforeOrEqualTo(OffsetDateTime.now());
    }

    @Test
    void 유저생성_실패__이메일이_공백인_경우() {
        var blankEmail = " ";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User(blankEmail, "김태욱", "1234"));
    }

    @Test
    void 유저생성_실패__이름이_공백인_경우() {
        var blankName = " ";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User("wook@naver.com", blankName, "1234"));
    }

    @Test
    void 유저생성_실패__비밀번호가_공백인_경우() {
        var blankPassword = " ";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User("wook@naver.com", "김태욱", blankPassword));
    }
}
