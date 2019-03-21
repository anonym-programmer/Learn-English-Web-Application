package pl.robert.api.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.user.domain.dto.CreateUserDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserValidator implements UserConstants {

    UserRepository repository;

    void checkInputData(CreateUserDTO dto, BindingResult result) {

        if (isLoginExist(dto.getLogin())) {
            result.rejectValue(F_LOGIN, C_EXIST, M_LOGIN_EXISTS);
        }

        if (isEmailExist(dto.getEmail())) {
            result.rejectValue(F_EMAIL, C_EXIST, M_EMAIL_EXISTS);
        }

        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            result.rejectValue(F_CONFIRMED_PASSWORD, C_NOT_MATCH, M_CONFIRMED_PASSWORD_NOT_MATCH);
        }
    }

    private boolean isLoginExist(String login) {
        return repository.findByLogin(login) != null;
    }

    private boolean isEmailExist(String email) {
        return repository.findByEmail(email) != null;
    }
}
