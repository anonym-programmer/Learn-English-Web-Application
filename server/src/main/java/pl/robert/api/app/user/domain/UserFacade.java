package pl.robert.api.app.user.domain;

import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.user.domain.dto.ChangeUserPasswordDTO;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;
import pl.robert.api.app.user.domain.dto.ForgotUserCredentialsDTO;
import pl.robert.api.core.security.dto.AuthUserDTO;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidator validator;
    UserService userService;
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

    public void generateResetPasswordToken(ForgotUserCredentialsDTO dto) {
        tokenService.generateResetPasswordToken(userService.findByEmail(dto.getEmail()));
    }

    public void changePassword(ChangeUserPasswordDTO dto, String resetPasswordToken) {
        User user = tokenService.findByConfirmationToken(resetPasswordToken).getUser();
        user.setPassword(UserFactory.passwordEncoder().encode(dto.getPassword()));
        userService.saveAndFlush(user);
        tokenService.deleteToken(resetPasswordToken);
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
}
