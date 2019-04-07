INSERT INTO roles (id, role)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO users (username, email, password, is_enabled)
VALUES ('a', 'a@a.com', '$2a$10$Y2xKL3PjHd257clTHpcJl.tg4qtFyDH1/Q8UESXboV9tMnXGRjShm', true),
       ('b', 'b@b.com', '$2a$10$QMlzlGyhELD1bhRQrJQ/3Oc.A6K5axHGiqXSp7ZkmoRRmhIdbhgCy', true),
       ('Bill Gates', 'microsoft@outlook.com', '$2a$10$kcKvLya3fvMwKbBycgStF.yf8PIiSwXMCxc8WyRD5vYI/cb3jYcy2', true),
       ('Mark Zuckerberg', 'mark.zuckerberg@facemail.com',
        '$2a$10$8gSiB2zsTed0rNr01IhhkumJEEJYHd0Jluzar1hoDzY0czxd4E4kK', true),
       ('Linus Torvalds', 'penguin@linux.com', '$2a$10$e6BSDr2.qNmlUjPiPxKtXOy6VXLNicBjJOmFfVCSe3x8Ce.69SPO6', true),
       ('George Hotz', 'comma@mail.com', '$2a$10$c74z6i5BEpZd7zxDt3Oh4ubXr6dvk6zS6TR3FGW09twI0o7nEkqve', true),
       ('James Gosling', 'james.java@mail.com', '$2a$10$X/G9UC9p2xDy0o8epoePCOzs/PGreS35kbrdUo51UjZgBcmiozikm', true),
       ('Rob', 'rob@gmail.com', '$2a$10$aIeVCFtezvuV9sIScXRB5OYSSrHbDZJGJpHlL6dDBUKQ1FNeqcYs2', true),
       ('Steve Jobs', 'iLoveApple@icloud.com', '$2a$10$kFFzJ/OTpPpiXEGeCJehsO6.g1CHVayzVyVLVfS3wJvhXUMJ/0lES', true);

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1);

INSERT INTO user_details (level, expierience, current_rank, user_id)
VALUES ('13', '13555', 'CHALLENGER', 1),
       ('2', '80', 'BRONZE', 2),
       ('3', '190', 'SILVER', 3),
       ('5', '365', 'GOLD', 4),
       ('11', '6795', 'MASTER', 5),
       ('12', '7295', 'MASTER', 6),
       ('9', '2400', 'DIAMOND', 7),
       ('7', '850', 'PLATINUM', 8),
       ('6', '700', 'GOLD', 9);

INSERT INTO posts (title, description, date, up_vote, down_vote, user_id)
VALUES ('Learning English',
        'Hello im penguin, how did you learn English? When did you started and whats level do you have?',
        '2019-02-15 07:10:52', 5, 1, 5),
       ('Apple', 'Are apple for noobs?', '2019-02-24 07:14:56', 10, 0, 9),
       ('British vs American', 'Differences between British and American English?', '2019-02-21 08:11:12', 6, 3, 4),
       ('In, On, At', 'When to use these words? Can someone explain it?', '2019-02-22 09:12:14', 2, 5, 3),
       ('Free time', 'What do you do in your free time?', '2019-02-23 06:13:55', 1, 9, 4),
       ('Hobby', 'Whats your hobby?', '2019-02-24 15:49:11', 6, 0, 6),
       ('Java', 'Is java hard to learn?', '2019-02-24 15:49:11', 2, 0, 7);
