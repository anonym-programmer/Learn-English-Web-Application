package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.user.domain.dto.ChangePasswordDTO;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;
import pl.robert.api.app.user.domain.dto.ForgotCredentialsDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserValidator implements UserConstants {

    UserService userService;
    TokenService tokenService;

    void checkInputData(CreateUserDTO dto, BindingResult result) {

        if (userService.isUsernameExist(dto.getUsername())) {
            result.rejectValue(F_USERNAME, C_EXISTS, M_USERNAME_EXISTS);
        }

        if (userService.isEmailExist(dto.getEmail())) {
            result.rejectValue(F_EMAIL, C_EXISTS, M_EMAIL_EXISTS);
        }

        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            result.rejectValue(F_CONFIRMED_PASSWORD, C_NOT_MATCH, M_CONFIRMED_PASSWORD_NOT_MATCH);
        }
    }

    void checkInputData(ForgotCredentialsDTO dto, BindingResult result) {

        if (userService.isEmailExist(dto.getEmail()) && !userService.findByEmail(dto.getEmail()).isEnabled()) {
            result.rejectValue(F_EMAIL, C_NOT_ENABLED, M_ACCOUNT_NOT_ENABLED);
        }

        if (userService.isEmailExist(dto.getEmail()) && tokenService.findByUser(userService.findByEmail(dto.getEmail())) != null) {
            result.rejectValue(F_EMAIL, C_SENT, M_TOKEN_SENT);
        }

        if (!userService.isEmailExist(dto.getEmail())) {
            result.rejectValue(F_EMAIL, C_NOT_EXISTS, M_EMAIL_NOT_EXISTS);
        }
    }

    void checkInputData(ChangePasswordDTO dto, BindingResult result) {

        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            result.rejectValue(F_CONFIRMED_PASSWORD, C_NOT_MATCH, M_CONFIRMED_PASSWORD_NOT_MATCH);
        }
    }
}
