package com.woogie.todo.core.user.domain;

import lombok.*;
import org.springframework.util.Assert;

import java.time.OffsetDateTime;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long id;
    private String email;
    private String name;
    private String password;
    private UserStatus status;
    private OffsetDateTime createdAt;

    public User(String email, String name, String password) {
        Assert.isTrue(!email.isBlank(), "이메일 공백 허용 안함");
        Assert.isTrue(!name.isBlank(), "이름 공백 허용 안함");
        Assert.isTrue(!password.isBlank(), "비밀번호 공백 허용 안함");

        this.email = email;
        this.name = name;
        this.password = password;
        this.status = UserStatus.ACTIVATED;
        this.createdAt = OffsetDateTime.now();
    }
}
