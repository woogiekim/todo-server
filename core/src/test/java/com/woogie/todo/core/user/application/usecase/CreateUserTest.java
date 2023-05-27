package com.woogie.todo.core.user.application.usecase;

import com.woogie.todo.core.support.BaseUseCaseTest;
import com.woogie.todo.core.user.application.usecase.CreateUser.CreateUserCommand;
import com.woogie.todo.core.user.domain.UserRepository;
import com.woogie.todo.core.user.domain.UserStatus;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CreateUserTest extends BaseUseCaseTest {

    @Autowired
    private com.woogie.todo.core.user.application.usecase.CreateUser createUser;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저생성() {
        var command = new CreateUserCommand("wook@naver.com", "김태욱", "1234");

        createUser.create(command);

        var foundUser = userRepository.findAll().stream().findFirst().orElseThrow();

        assertThat(foundUser.getEmail()).isEqualTo("wook@naver.com");
        assertThat(foundUser.getName()).isEqualTo("김태욱");
        assertThat(foundUser.getPassword()).isEqualTo("1234");
        assertThat(foundUser.getStatus()).isEqualTo(UserStatus.ACTIVATED);
    }

    @Test
    void 유저생성_실패__이메일형식이_아닌_경우() {
        var fakeEmail = "wook";

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateUserCommand(fakeEmail, "김태욱", "1234"));
    }

    @Test
    void 유저생성_실패_이름이_공백인_경우() {
        var fakeName = " ";

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateUserCommand("wook@naver.com", fakeName, "1234"));
    }

    @Test
    void 유저생성_실패__비밀번호가_공백인_경우() {
        var fakePassword = " ";

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new CreateUserCommand("wook@naver.com", "김태욱", fakePassword));
    }
}
