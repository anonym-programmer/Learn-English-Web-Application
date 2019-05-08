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

    static UserOwnProfileQuery queryUserOwnProfile(User user) {
        return UserOwnProfileQuery.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().size() == 1 ? USER : USER_ADMIN)
                .level(user.getUserDetails().getLevel())
                .experience(user.getUserDetails().getExpierience())
                .currentRank(user.getUserDetails().getCurrentRank())
                .leftExperienceToTheNextLevel(user.getUserDetails().getLeftExperienceToTheNextLevel())
                .currentExperienceInPercents(user.getUserDetails().getCurrentExperienceInPercents())
                .numberOfWins(user.getUserDetails().getNumberOfWins())
                .numberOfLoses(user.getUserDetails().getNumberOfLoses())
                .numberOfDraws(user.getUserDetails().getNumberOfDraws())
                .numberOfPosts(user.getUserDetails().getNumberOfPosts())
                .numberOfComments(user.getUserDetails().getNumberOfComments())
                .numberOfVotes(user.getUserDetails().getNumberOfVotes())
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

    static UserProfileQuery queryUserProfile(User user) {
        return UserProfileQuery.builder()
                .username(user.getUsername())
                .level(user.getUserDetails().getLevel())
                .experience(user.getUserDetails().getExpierience())
                .currentRank(user.getUserDetails().getCurrentRank())
                .leftExperienceToTheNextLevel(user.getUserDetails().getLeftExperienceToTheNextLevel())
                .currentExperienceInPercents(user.getUserDetails().getCurrentExperienceInPercents())
                .numberOfWins(user.getUserDetails().getNumberOfWins())
                .numberOfLoses(user.getUserDetails().getNumberOfLoses())
                .numberOfDraws(user.getUserDetails().getNumberOfDraws())
                .numberOfPosts(user.getUserDetails().getNumberOfPosts())
                .numberOfComments(user.getUserDetails().getNumberOfComments())
                .numberOfVotes(user.getUserDetails().getNumberOfVotes())
                .build();
    }

    static RandomUsernameQuery queryRandomUsername(String defenderUsername) {
        return RandomUsernameQuery.builder()
                .defenderUsername(defenderUsername)
                .build();
    }
}
