package pl.robert.api.app.user.domain;

import org.springframework.security.core.Authentication;
import pl.robert.api.app.user.query.UserAuthQuery;
import pl.robert.api.app.user.query.UserQuery;

class UserQueryFactory {

    static UserAuthQuery queryUserAuth(Authentication auth) {
        return UserAuthQuery
                .builder()
                .username(auth.getName())
                .roles(auth.getAuthorities().size() == 2 ? "User, Admin" : "User")
                .isAuthenticated(auth.isAuthenticated())
                .build();
    }

    static UserQuery queryUserAndUserDetails(User user, UserDetails details) {
        return UserQuery
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().size() == 2 ? "User, Admin" : "User")
                .level(details.getLevel())
                .experience(details.getExpierience())
                .currentRank(details.getCurrentRank())
                .build();
    }
}
