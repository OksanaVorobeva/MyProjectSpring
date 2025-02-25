CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    birth_date DATE,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    role VARCHAR(32),
    image varchar(128),
    password varchar(128) default '{noop}123'
);