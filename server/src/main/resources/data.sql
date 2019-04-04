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
                  ('Sample title', 'Sample description ...', '2019-02-15 07:10:52', 5, 1, 1),
                  ('Sample title', 'Sample description ...', '2019-01-21 08:11:53', 6, 3, 1),
                  ('Sample title', 'Sample description ...', '2019-03-22 09:12:54', 2, 5, 1),
                  ('Sample title', 'Sample description ...', '2019-04-10 06:13:55', 9, 0, 2),
                  ('Sample title', 'Sample description ...', '2019-02-12 05:14:56', 7, 2, 2);
