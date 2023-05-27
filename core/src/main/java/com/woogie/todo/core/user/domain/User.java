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

    public User(@NonNull String email, String name, String password) {
        Assert.notNull(email, "email is null");
        Assert.notNull(name, "name is null");
        Assert.notNull(password, "password is null");

        this.email = email;
        this.name = name;
        this.password = password;
        this.status = UserStatus.ACTIVATED;
        this.createdAt = OffsetDateTime.now();
    }
}
