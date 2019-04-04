INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users (username, email, password, is_enabled) VALUES
                  ('a', 'a@a.com', '$2a$10$Y2xKL3PjHd257clTHpcJl.tg4qtFyDH1/Q8UESXboV9tMnXGRjShm', true),
                  ('b', 'b@b.com', '$2a$10$QMlzlGyhELD1bhRQrJQ/3Oc.A6K5axHGiqXSp7ZkmoRRmhIdbhgCy', true);

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1), (1, 2),
                                                   (2, 1);

INSERT INTO  user_details (level, expierience, current_rank, user_id) VALUES
                          ('13', '13556', 'CHALLENGER', 1),
                          ('5' , '365'  , 'BRONZE', 2);

INSERT INTO posts (title, description, date, up_vote, down_vote, user_id) VALUES
                  ('Sample title', 'Sample description', '2019-02-15 07:10:52', 5, 1, 1),
                  ('Sample title', 'Sample description', '2019-02-21 08:11:12', 6, 3, 1),
                  ('Sample title', 'Sample description', '2019-02-22 09:12:14', 2, 5, 1),
                  ('Sample title', 'Sample description', '2019-02-23 06:13:55', 9, 0, 2),
                  ('Sample title', 'Sample description', '2019-02-24 07:14:56', 7, 2, 2),
                  ('Sample title', 'Sample description', '2019-02-24 15:49:11', 6, 0, 1),
                  ('Sample title', 'Sample description', '2019-02-24 02:50:50', 1, 5, 2),
                  ('Sample title', 'Sample description', '2019-02-27 05:11:56', 5, 5, 2),
                  ('Sample title', 'Sample description', '2019-03-04 06:10:10', 2, 3, 1),
                  ('Sample title', 'Sample description', '2019-03-11 12:05:45', 2, 9, 2),
                  ('Sample title', 'Sample description', '2019-03-10 16:45:44', 1, 5, 2),
                  ('Sample title', 'Sample description', '2019-03-25 20:15:05', 7, 6, 1),
                  ('Sample title', 'Sample description', '2019-04-12 04:44:56', 9, 1, 2);
