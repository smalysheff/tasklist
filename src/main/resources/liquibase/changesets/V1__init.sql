--liquibase formatted sql

--changeset MalyshevSV:HM-02

create table if not exists users
(
    id       bigserial,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    constraint pk_users_id primary key (id)
);

create table if not exists tasks
(
    id              bigserial,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    expiration_date timestamp    null,
    constraint pk_tasks_id primary key (id)
);

create table if not exists users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_users_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_role_users foreign key (user_id) references users (id) on delete cascade on update no action
);

create table if not exists tasks_images
(
    task_id bigint       not null,
    image   varchar(255) not null,
    constraint fk_tasks_images_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);