CREATE TABLE IF NOT EXISTS directory
(
    id                BIGSERIAL PRIMARY KEY,
    feeling           varchar(128),
    example_situation varchar,
    bodily_sensations varchar,
    thoughts          varchar,
    behavior          varchar
);

CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(64) NOT NULL UNIQUE,
    birth_date DATE,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    role       VARCHAR(32),
    image      varchar(128),
    password   varchar(128)
);

CREATE TABLE IF NOT EXISTS notes
(
    id                       BIGSERIAL PRIMARY KEY,
    created_date             DATE,
    feeling                  varchar(128),
    life_situation           varchar,
    telex_sensation          varchar,
    your_actions             varchar,
    my_thoughts_about_others varchar,
    my_thoughts              varchar,
    user_id                  BIGINT  REFERENCES users (id)  ON DELETE CASCADE

);