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
VALUES ('13', '13555', 'Challenger', 1),
       ('2', '80', 'Bronze', 2),
       ('3', '190', 'Silver', 3),
       ('5', '365', 'Gold', 4),
       ('11', '6795', 'Master', 5),
       ('12', '7295', 'Master', 6),
       ('9', '2400', 'Diamond', 7),
       ('7', '850', 'Platinum', 8),
       ('6', '700', 'Gold', 9);

INSERT INTO posts (title, description, date, up_vote, down_vote, user_id)
VALUES ('Learning English',
        'Hello im penguin, how did you learn English? When did you started and whats level do you have?',
        '2019-02-15 07:10:52', 3, 0, 5),
       ('Apple', 'Is apple a cool company?', '2019-02-24 07:14:56', 0, 7, 9),
       ('British vs American', 'Differences between British and American English?', '2019-02-21 08:11:12', 2, 2, 4),
       ('In, On, At', 'When to use these words? Can someone explain it?', '2019-02-22 09:12:14', 5, 1, 3),
       ('Free time', 'What do you do in your free time?', '2019-02-23 06:13:55', 2, 5, 4);

INSERT INTO votes (post_id, user_id, date, type)
VALUES (1, 3, '2019-02-15 08:10:52', 'Y'),
       (1, 4, '2019-02-16 08:15:38', 'Y'),
       (1, 6, '2019-02-16 14:45:23', 'Y'),
       (2, 3, '2019-02-21 14:59:12', 'N'),
       (2, 4, '2019-02-22 20:11:03', 'N'),
       (2, 5, '2019-02-22 14:45:23', 'N'),
       (2, 6, '2019-02-22 20:00:07', 'N'),
       (2, 7, '2019-02-23 21:02:23', 'N'),
       (2, 8, '2019-02-24 22:22:07', 'N'),
       (2, 9, '2019-03-01 23:44:21', 'Y'),
       (3, 3, '2019-03-04 08:59:46', 'Y'),
       (3, 5, '2019-03-04 03:25:12', 'Y'),
       (3, 8, '2019-03-04 13:44:55', 'N'),
       (3, 9, '2019-03-05 03:10:12', 'N'),
       (4, 4, '2019-03-05 10:11:09', 'Y'),
       (4, 5, '2019-03-05 20:23:54', 'Y'),
       (4, 6, '2019-03-10 02:12:12', 'N'),
       (4, 7, '2019-03-11 09:54:21', 'Y'),
       (4, 8, '2019-03-11 15:17:13', 'Y'),
       (4, 9, '2019-03-11 16:30:15', 'Y'),
       (5, 3, '2019-03-15 17:20:45', 'N'),
       (5, 4, '2019-03-16 09:19:55', 'Y'),
       (5, 5, '2019-03-20 07:05:23', 'N'),
       (5, 6, '2019-03-21 12:04:29', 'Y'),
       (5, 7, '2019-03-22 22:22:30', 'N'),
       (5, 8, '2019-03-25 02:09:04', 'N'),
       (5, 9, '2019-03-27 23:11:59', 'N');

INSERT INTO comments (post_id, user_id, text, date)
VALUES (1, 3, 'Hi! I always wanted to meet u Mr. LinuX Torvalds! Tbh I learnt english by programming, peace!',
        '2019-02-15 08:12:44'),
       (1, 4,
        'Hello :) I learnt many languages like for ex. Polish, German and English by creating an internationalization
         app. If u want to see my app, type www.facebook.com in your browser :) Have a nice day guys!',
        '2019-02-16 08:20:12'),
       (1, 5, 'Yeah, but which english level have u got guys?', '2019-02-16 14:46:23'),
       (1, 3, 'Ive got a C1 level I think, but I want to have C2 as obvious :)', '2019-02-17 08:22:10'),
       (1, 4, 'Ive got a C2+ level because I was born in UK.', '2019-02-17 08:22:10');

INSERT INTO questions (question, answers, correct_answer_full_form, correct_answer_short_form)
VALUES ('Whats ... name?', 'you:your:yours:yourse', 'your', 'b'),
       ('He goes to his guitar lessons ...', 'by underground:on underground:with underground:in underground', 'by underground', 'a'),
       ('We havent got ... Champagne', 'a lot:little:too:much', 'much', 'd'),
       ('David is the boss, you need to speak to ...', 'it:him:her:them', 'him', 'b'),
       ('She ... Supper with us last Friday', 'hadnt:no had:didnt have got:didnt have', 'didnt have', 'd'),
       ('I havent got ...', 'no brothers or sisters:brothers or sisters:any brothers or sisters:some brothers and sisters', 'any brother or sisters', 'c'),
       ('George ... fly to Stockholm tomorrow. ', 'to going:goes to:is going to:go to', 'is going to', 'c'),
       ('I wanted an orange car, but they only had ...', 'a one red:one red:a red one:a red', 'a red one', 'c'),
       ('Have you ...?', 'got any friends in Barcelona:not got no friends in Barcelona:in Barcelona any friends:friends in Barcelona got', 'got any friends in Barcelona', 'a'),
       ('There is ... of beer left from the party ', 'little:much:a lot:too', 'a lot', 'c');

INSERT INTO oponents (my_answers, answers_status, gained_xp, result, user_id)
VALUES ('b:a:c:b:a', '1:1:0:1:0', '60', 'WIN', 5),
       ('d:a:b:c:a', '0:1:0:0:0', '15', 'LOSE', 6),
       ('b:d:d:c:a', '1:1:1:1:1', NULL, 'NONE', 6),
       (NULL, NULL, NULL, 'NONE', 5);

INSERT INTO challenges (attacker_id, defender_id, date_of_creation, status)
VALUES (1, 2, '2019-04-17 18:35:52', 'COMPLETED'),
       (3, 4, '2019-04-17 19:21:52', 'PENDING');

INSERT INTO challenges_questions (challenge_id, questions_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 1),
       (2, 3),
       (2, 5),
       (2, 7),
       (2, 9);
