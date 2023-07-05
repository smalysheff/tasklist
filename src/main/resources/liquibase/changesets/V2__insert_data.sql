--liquibase formatted sql

--changeset MalyshevSV:HM-02

insert into users (name, username, password)
values ('John', 'Doe', '$2a$10$rWfXP0fyK0gwu7U0j4EsruaSK5713NQSl32iOOaL1wCFcUTjJ0FvC'),
       ('James', 'Gosling', '$2a$10$0akTcgFeV7P3pDdAFCu8qOzKltFmZBPhuBOBqLxn3VZbtovLfiLEO');

insert into tasks (title, description, status, expiration_date)
values ('Buy cheese', null, 'TODO', '2023-07-01 12:00:00'),
       ('Do homework', 'Math, Physics, Literature', 'IN_PROGRESS', '2023-07-02 12:00:00'),
       ('Clean rooms', null, 'TODO', null),
       ('Call Youriy', 'Ask about meeting', 'TODO', '2023-07-04 12:00:00');

insert into users_tasks (task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_roles(user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');