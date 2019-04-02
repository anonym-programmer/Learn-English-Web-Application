package pl.robert.api.app.user.domain;

import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.user.domain.dto.ChangeUserEmailDTO;
import pl.robert.api.app.user.domain.dto.ChangeUserPasswordDTO;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;
import pl.robert.api.app.user.domain.dto.ForgotUserCredentialsDTO;
import pl.robert.api.app.user.query.UserAuthQuery;
import pl.robert.api.app.user.query.UserQuery;
import pl.robert.api.core.security.dto.AuthUserDTO;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidator validator;
    UserService userService;
    UserDetailsService detailsService;
    TokenService tokenService;

    public void add(CreateUserDTO dto) {
        User user = UserFactory.create(dto);
        userService.saveAndFlush(user);
        tokenService.generateRegisterToken(user);
    }

    public void checkInputData(CreateUserDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ForgotUserCredentialsDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ChangeUserPasswordDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ChangeUserEmailDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void generateResetPasswordToken(ForgotUserCredentialsDTO dto) {
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

    public User findUserByConfirmationToken(String token) {
        return tokenService.findByConfirmationToken(token).getUser();
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

    public Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        return userService.fillMultiMapWithErrors(result);
    }

    public Optional<AuthUserDTO> findAuthByUsername(String username) {
        return userService.findAuthByUsername(username);
    }

    public User findUserByUsername(String username) {
        return userService.findByUsername(username);
    }

    public UserAuthQuery queryUserAuth(Authentication auth) {
        return UserQueryFactory.queryUserAuth(auth);
    }

    public UserQuery queryUserProfile(String username) {
        User user = findUserByUsername(username);
        UserDetails userDetails = detailsService.findUserDetailsById(user.getId());
        return UserQueryFactory.queryUserAndUserDetails(user, userDetails);
    }
}
