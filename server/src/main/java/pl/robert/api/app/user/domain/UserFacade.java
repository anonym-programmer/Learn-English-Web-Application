package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.user.domain.dto.*;
import pl.robert.api.app.user.query.UserAuthQuery;
import pl.robert.api.app.user.query.UserOwnProfileQuery;
import pl.robert.api.app.user.query.UserProfileQuery;
import pl.robert.api.app.user.query.UserQuery;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidator validator;
    UserService userService;
    UserDetailsService detailsService;
    TokenService tokenService;

    public void checkInputData(CreateUserDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ForgotUserCredentialsDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ChangeUserPasswordDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ChangeUserEmailDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void add(CreateUserDto dto) {
        User user = UserFactory.create(dto);
        userService.saveAndFlush(user);
        tokenService.generateRegisterToken(user);
    }

    public void generateResetPasswordToken(ForgotUserCredentialsDto dto) {
        tokenService.generateResetPasswordToken(userService.findByEmail(dto.getEmail()));
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(UserFactory.passwordEncoder().encode(newPassword));
        userService.saveAndFlush(user);
    }

    public void changeEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        userService.saveAndFlush(user);
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

    public boolean isUserExists(String username) {
        return userService.isUsernameExist(username);
    }

    public User findUserByConfirmationToken(String token) {
        return tokenService.findByConfirmationToken(token).getUser();
    }

    public User findUserByUsername(String username) {
        return userService.findByUsername(username);
    }

    public Optional<AuthUserDto> findAuthByUsername(String username) {
        return userService.findAuthByUsername(username);
    }

    public UserAuthQuery queryUserAuth(Authentication auth) {
        return UserQueryFactory.queryUserAuth(auth);
    }

    public UserOwnProfileQuery queryUserOwnProfile(String username) {
        User user = findUserByUsername(username);
        UserDetails userDetails = detailsService.findUserDetailsById(user.getId());
        detailsService.updateUserDetails(userDetails);
        return UserQueryFactory.queryUserOwnProfile(user, userDetails);
    }

    public UserProfileQuery queryUserProfile(String username) {
        User user = findUserByUsername(username);
        UserDetails userDetails = detailsService.findUserDetailsById(user.getId());
        detailsService.updateUserDetails(userDetails);
        return UserQueryFactory.queryUserProfile(user, userDetails);
    }

    public Page<UserQuery> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
