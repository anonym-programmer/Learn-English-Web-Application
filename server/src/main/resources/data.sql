INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users (login, email, password, is_enabled) VALUES
                  ('a', 'a@a.com', '$2a$10$Y2xKL3PjHd257clTHpcJl.tg4qtFyDH1/Q8UESXboV9tMnXGRjShm', true),
                  ('b', 'b@b.com', '$2a$10$QMlzlGyhELD1bhRQrJQ/3Oc.A6K5axHGiqXSp7ZkmoRRmhIdbhgCy', true);

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1), (1, 2),
                                                   (2, 1);
