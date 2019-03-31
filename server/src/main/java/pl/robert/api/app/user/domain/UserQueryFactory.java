package pl.robert.api.app.user.domain;

import pl.robert.api.app.user.query.UserQuery;

public class UserQueryFactory {

    public static UserQuery query(User user) {
        return UserQuery
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles().size() == 2 ? "ROLE_USER, ROLE_ADMIN" : "ROLE_USER")
                .build();
    }
}
