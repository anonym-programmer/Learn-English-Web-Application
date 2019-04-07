package pl.robert.api.app.user.domain;

import org.springframework.security.core.Authentication;
import pl.robert.api.app.user.query.UserAuthQuery;
import pl.robert.api.app.user.query.UserOwnProfileQuery;

import static pl.robert.api.app.user.domain.UserConstants.ROLE_USER;
import static pl.robert.api.app.user.domain.UserConstants.ROLE_USER_ADMIN;

class UserQueryFactory {

    static UserAuthQuery queryUserAuth(Authentication auth) {
        return UserAuthQuery
                .builder()
                .username(auth.getName())
                .roles(auth.getAuthorities().size() == 2 ? "User, Admin" : "User")
                .isAuthenticated(auth.isAuthenticated())
                .build();
    }

    static UserOwnProfileQuery queryUserOwnProfile(User user, UserDetails details) {
        return UserOwnProfileQuery
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().size() == 2 ? ROLE_USER_ADMIN : ROLE_USER)
                .level(details.getLevel())
                .experience(details.getExpierience())
                .currentRank(details.getCurrentRank())
                .leftExperienceToTheNextLevel(details.getLeftExperienceToTheNextLevel())
                .currentExperienceInPercents(details.getCurrentExperienceInPercents())
                .build();
    }

    static UserOwnProfileQuery queryUserProfile(User user, UserDetails details) {
        return UserOwnProfileQuery
                .builder()
                .username(user.getUsername())
                .level(details.getLevel())
                .experience(details.getExpierience())
                .currentRank(details.getCurrentRank())
                .leftExperienceToTheNextLevel(details.getLeftExperienceToTheNextLevel())
                .currentExperienceInPercents(details.getCurrentExperienceInPercents())
                .build();
    }
}
