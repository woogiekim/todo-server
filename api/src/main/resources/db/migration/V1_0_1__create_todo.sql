create table todo
(
    id          bigint auto_increment
        primary key,
    user_id     bigint null,
    title       varchar(300) null,
    description text null,
    startTime   timestamp   not null,
    endTime     timestamp   not null,
    status      varchar(20) not null,
    created_at  timestamp   not null,
    constraint todo_user_id_fk
        foreign key (user_id) references todotest.user (id)
);

