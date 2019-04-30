package pl.robert.api.app.user.domain;

import org.springframework.security.core.Authentication;
import pl.robert.api.app.user.query.*;

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

    static UserChallengeProfileQuery queryUserChallengeProfile(String username, UserDetails details) {
        return UserChallengeProfileQuery.builder()
                .username(username)
                .level(details.getLevel())
                .numberOfWins(details.getNumberOfWins())
                .numberOfLoses(details.getNumberOfLoses())
                .numberOfDraws(details.getNumberOfDraws())
                .build();
    }

    static UserForumProfileQuery queryUserForumProfile(String username, UserDetails details) {
        return UserForumProfileQuery.builder()
                .username(username)
                .level(details.getLevel())
                .numberOfPosts(details.getNumberOfPosts())
                .numberOfComments(details.getNumberOfComments())
                .numberOfVotes(details.getNumberOfVotes())
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
