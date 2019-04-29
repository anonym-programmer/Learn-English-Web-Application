package pl.robert.api.app.user.domain;

import org.springframework.security.core.Authentication;
import pl.robert.api.app.user.query.AuthUserQuery;
import pl.robert.api.app.user.query.UserOwnProfileQuery;
import pl.robert.api.app.user.query.UserProfileQuery;
import pl.robert.api.app.user.query.RandomUserQuery;

import static pl.robert.api.app.shared.Constants.*;

class UserQueryFactory {

    static AuthUserQuery queryUserAuth(Authentication auth) {
        return AuthUserQuery.builder()
                .username(auth.getName())
                .roles(auth.getAuthorities().size() == 1 ? USER : USER_ADMIN)
                .isAuthenticated(String.valueOf(auth.isAuthenticated()))
                .build();
    }

    static UserOwnProfileQuery queryUserOwnProfile(User user, UserDetails details) {
        return UserOwnProfileQuery.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().size() == 1 ? USER : USER_ADMIN)
                .level(details.getLevel())
                .experience(details.getExpierience())
                .currentRank(details.getCurrentRank())
                .leftExperienceToTheNextLevel(details.getLeftExperienceToTheNextLevel())
                .currentExperienceInPercents(details.getCurrentExperienceInPercents())
                .build();
    }

    static UserProfileQuery queryUserProfile(User user, UserDetails details) {
        return UserProfileQuery.builder()
                .username(user.getUsername())
                .level(details.getLevel())
                .experience(details.getExpierience())
                .currentRank(details.getCurrentRank())
                .leftExperienceToTheNextLevel(details.getLeftExperienceToTheNextLevel())
                .currentExperienceInPercents(details.getCurrentExperienceInPercents())
                .build();
    }

    static RandomUserQuery queryRandomUser(String defenderUsername) {
        return RandomUserQuery.builder()
                .defenderUsername(defenderUsername)
                .build();
    }
}
