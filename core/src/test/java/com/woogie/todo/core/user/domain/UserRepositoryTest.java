package com.woogie.todo.core.user.domain;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저생성() {
        var user = new User("wook@naver.com", "김태욱", "1234");

        userRepository.save(user);

        var foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getId()).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("wook@naver.com");
        assertThat(foundUser.getName()).isEqualTo("김태욱");
        assertThat(foundUser.getPassword()).isEqualTo("1234");
        assertThat(foundUser.getStatus()).isEqualTo(UserStatus.ACTIVATED);
        assertThat(foundUser.getCreatedAt()).isEqualToIgnoringMinutes(OffsetDateTime.now());
    }
}
