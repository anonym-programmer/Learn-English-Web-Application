package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.user.domain.dto.*;
import pl.robert.api.app.user.query.*;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidator validator;
    UserService userService;
    UserDetailsService detailsService;
    TokenService tokenService;

    public void checkInputData(CreateUserDto dto, BindingResult result) {
        if (!result.hasErrors()) validator.checkInputData(dto, result);
    }

    public void checkInputData(ForgotUserCredentialsDto dto, BindingResult result) {
        if (!result.hasErrors()) validator.checkInputData(dto, result);
    }

    public void checkInputData(ChangeUserPasswordDto dto, BindingResult result) {
        if (!result.hasErrors()) validator.checkInputData(dto, result);
    }

    public void checkInputData(ChangeUserEmailDto dto, BindingResult result) {
        if (!result.hasErrors()) validator.checkInputData(dto, result);
    }

    public boolean isInputDataCorrect(DeleteUserDto dto) {
        return validator.isInputDataCorrect(dto);
    }

    public void add(CreateUserDto dto) {
        userService.save(UserFactory.create(dto));
    }

    public void deleteUserById(long id) {
        userService.delete(userService.findById(id));
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(UserFactory.passwordEncoder().encode(newPassword));
        userService.save(user);
    }

    public void changeEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        userService.save(user);
    }

    public boolean isUsernameNotExists(String username) {
        return userService.isUsernameNotExist(username);
    }

    public void generateRegisterToken(String username) {
        tokenService.generateRegisterToken(findUserByUsername(username));
    }

    public void generateResetPasswordToken(ForgotUserCredentialsDto dto) {
        tokenService.generateResetPasswordToken(userService.findByEmail(dto.getEmail()));
    }

    public void deleteToken(String token) {
        tokenService.deleteToken(token);
    }

    public boolean isTokenCorrect(String token) {
        tokenService.cleanAllExpiredTokens();
        return tokenService.findByConfirmationToken(token) != null;
    }

    public boolean confirmRegisterToken(String confirmationToken) {
        return tokenService.confirmRegisterToken(confirmationToken);
    }

    public void addExperience(String username, int experience) {
        detailsService.addExperience(findUserByUsername(username).getUserDetails(), experience);
    }

    public void incrementUserWins(String username) {
        detailsService.incrementUserWins(findUserByUsername(username).getUserDetails());
    }

    public void incrementUserLoses(String username) {
        detailsService.incrementUserLoses(findUserByUsername(username).getUserDetails());
    }

    public void incrementUserDraws(String username) {
        detailsService.incrementUserDraws(findUserByUsername(username).getUserDetails());
    }

    public void incrementUserPosts(String username) {
        detailsService.incrementUserPosts(findUserByUsername(username).getUserDetails());
    }

    public void incrementUserComments(String username) {
        detailsService.incrementUserComments(findUserByUsername(username).getUserDetails());
    }

    public void incrementUserVotes(String username) {
        detailsService.incrementUserVotes(findUserByUsername(username).getUserDetails());
    }

    public User findUserByConfirmationToken(String token) {
        return tokenService.findByConfirmationToken(token).getUser();
    }

    public Page<UserQuery> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    public User findUserByUsername(String username) {
        return userService.findByUsername(username);
    }

    public Optional<SignInDto> querySignInByUsername(String username) {
        return userService.querySignInByUsername(username);
    }

    public AuthUserQuery queryUserAuth(Authentication auth) {
        return UserQueryFactory.queryUserAuth(auth);
    }

    public UserOwnProfileQuery queryUserOwnProfile(String username) {
        return UserQueryFactory.queryUserOwnProfile(detailsService.updateUserDetails(findUserByUsername(username)));
    }

    public UserChallengeProfileQuery queryUserChallengeProfile(String username) {
        return UserQueryFactory.queryUserChallengeProfile(username, userService.findByUsername(username).getUserDetails());
    }

    public UserForumProfileQuery queryUserForumProfile(String username) {
        return UserQueryFactory.queryUserForumProfile(username, userService.findByUsername(username).getUserDetails());
    }

    public UserProfileQuery queryUserProfile(String username) {
        return UserQueryFactory.queryUserProfile(detailsService.updateUserDetails(findUserByUsername(username)));
    }

    public RandomUsernameQuery queryRandomUsername(String attackerUsername) {
        return UserQueryFactory.queryRandomUsername(userService.queryRandomUsername(attackerUsername));
    }
}
