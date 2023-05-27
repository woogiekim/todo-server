package com.woogie.todo.core.todo.domain;

import com.woogie.todo.core.support.BaseEntity;
import com.woogie.todo.core.user.domain.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.OffsetDateTime;

@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Todo extends BaseEntity {

    private User user;
    private String title;
    private String description;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private TodoStatus status;
    private OffsetDateTime createdAt;

    public Todo(User user, String title, String description, OffsetDateTime startTime, OffsetDateTime endTime) {
        Assert.notNull(user, "user is null");
        Assert.notNull(startTime, "startTime is null");
        Assert.notNull(endTime, "endTime is null");
        Assert.isTrue(startTime.isBefore(endTime) || startTime.isEqual(endTime), "The endTime is earlier than startTime");

        this.user = user;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = TodoStatus.IN_PROGRESS;
        this.createdAt = OffsetDateTime.now();
    }
}
