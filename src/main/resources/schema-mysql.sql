DROP TABLE IF EXISTS order_dish;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS courier_reviews;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS couriers;
DROP TABLE IF EXISTS USER_AUTHORITY;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS AUTHORITY;

CREATE TABLE restaurants (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     address VARCHAR(50),
     email VARCHAR(50),
     opening_hours VARCHAR(50)
);

CREATE TABLE clients (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     address VARCHAR(50),
     email VARCHAR(50)
);

CREATE TABLE couriers (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     phone_number VARCHAR(50),
     available BOOLEAN
);

CREATE TABLE courier_reviews (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     stars BIGINT,
     courier_id BIGINT,
     client_id BIGINT
);

CREATE TABLE dishes (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     quantity BIGINT,
     price FLOAT,
     restaurant_id BIGINT
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50),
    client_id BIGINT,
    courier_id BIGINT,
    restaurant_id BIGINT
);

CREATE TABLE order_dish (
    order_id BIGINT,
    dish_id BIGINT
);

CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true,
    associated_id BIGINT,
    account_non_expired BOOLEAN NOT NULL DEFAULT true,
    account_non_locked BOOLEAN NOT NULL DEFAULT true,
    credentials_non_expired BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE AUTHORITY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE USER_AUTHORITY (
    user_id BIGINT,
    authority_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES USERS(id),
    FOREIGN KEY (authority_id) REFERENCES AUTHORITY(id),
    PRIMARY KEY (user_id, authority_id)
);
