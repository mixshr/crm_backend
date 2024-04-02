create table if not exists users
(
    id          uuid        not null
        constraint user_pk primary key,
    email       text unique not null,
    username    text        not null,
    password    text        not null,
    role        text        null,

    created_at  timestamp default now(),
    updated_at  timestamp default now(),
    deleted_at  timestamp default null
);