create table passengers
(
    id   identity     not null
        constraint table_name_pk primary key,
    name varchar(255) not null,
    age  integer      not null
);