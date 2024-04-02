create table if not exists orders
(
    id          uuid         not null
        constraint order_pk primary key,
    user_id     uuid         not null
        constraint user_order_fk references users,
    title       varchar(150) not null,
    description text         not null,
    status      varchar(50)  not null,
    planned_at  timestamp    not null,

    created_at  timestamp default now(),
    updated_at  timestamp default now(),
    deleted_at  timestamp default null
);