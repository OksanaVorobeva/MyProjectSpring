INSERT INTO users (birth_date, first_name, last_name, role, username)
VALUES ('1982-08-20', 'Oksana', 'Vorobeva', 'ADMIN', 'oksana@gmail.ru'),
       ('1992-09-24', 'Ivan', 'Ivanov', 'USER', 'ivan@gmail.ru'),
       ('2001-08-16', 'Pert', 'Petrov', 'USER', 'petr@gmail.ru');

INSERT INTO directory(feeling, example_situation, bodily_sensations, thoughts, behavior)
VALUES ('злость', 'ejejejej', 'fkfkfkf', 'jdjdjdjd', 'ddjdjdj'),
       ('злость', 'ejejejej', 'fkfkfkf', 'jdjdjdjd', 'ddjdjdj');

INSERT INTO notes(created_date, feeling, life_situation, telex_sensation, your_actions, my_thoughts_about_others,
                  my_thoughts, user_id)
VALUES ('2025-02-27', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'oooooo',
        (SELECT id from users where users.id = 1)),
       ('2025-02-26', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'mmmmmm',
        (SELECT id from users where users.id = 1)),
       ('2025-02-25', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'lllllll',
        (SELECT id from users where users.id = 2)),
       ('2025-02-24', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'lllllll',
        (SELECT id from users where users.id = 2)),
       ('2025-02-23', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'lllllll',
        (SELECT id from users where users.id = 3)),
       ('2025-02-22', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'kkkkkkk', 'lllllll',
        (SELECT id from users where users.id = 3));

