create schema if not exists shop;

create table if not exists shop.product (
    identifier integer,
    name varchar(32),
    price integer
    );