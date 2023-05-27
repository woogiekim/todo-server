package com.woogie.todo.core.user.domain;

import com.woogie.todo.core.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    private String email;
    private String name;
    private String password;
    private UserStatus status;
    private OffsetDateTime createdAt;

    public User(String email, String name, String password) {
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
