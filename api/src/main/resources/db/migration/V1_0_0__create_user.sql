create table user
(
    id         bigint auto_increment
        primary key,
    email      varchar(50) not null,
    name       varchar(50) not null,
    password   varchar(80) not null,
    status     varchar(20) not null,
    created_at timestamp   not null
);