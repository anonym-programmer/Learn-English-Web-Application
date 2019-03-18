package pl.robert.api.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.user.domain.dto.CreateUserDTO;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserValidator implements UserValidatorConstants {

    UserRepository repository;

    void checkInputData(CreateUserDTO dto, BindingResult result) {
        dto.setLogin(dto.getLogin().trim());

        if (dto.getLogin().isEmpty()) {
            result.rejectValue(F_LOGIN, C_EMPTY, M_LOGIN_EMPTY);
        }

        if (isLengthLowerThanGivenField(dto.getLogin(), COL_LENGTH_MIN_LOGIN)) {
            result.rejectValue(F_LOGIN, C_MIN_LENGTH, M_LOGIN_MIN_LENGTH);
        }

        if (isLengthHigherThanGivenField(dto.getLogin(), COL_LENGTH_MAX_LOGIN)) {
            result.rejectValue(F_LOGIN, C_MAX_LENGTH, M_LOGIN_MAX_LENGTH);
        }

        if (isLoginExist(dto.getLogin())) {
            result.rejectValue(F_LOGIN, C_EXIST, M_LOGIN_EXISTS);
        }

        if (dto.getPassword().isEmpty()) {
            result.rejectValue(F_PASSWORD, C_EMPTY, M_PASSWORD_EMPTY);
        }

        if (isLengthLowerThanGivenField(dto.getPassword(), COL_LENGTH_MIN_PASSWORD)) {
            result.rejectValue(F_PASSWORD, C_MIN_LENGTH, M_PASSWORD_MIN_LENGTH);
        }

        if (isLengthHigherThanGivenField(dto.getPassword(), COL_LENGTH_MAX_PASSWORD)) {
            result.rejectValue(F_PASSWORD, C_MIN_LENGTH, M_PASSWORD_MAX_LENGTH);
        }

        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            result.rejectValue(F_CONFIRMED_PASSWORD, C_NOT_MATCH, M_CONFIRMED_PASSWORD_NOT_MATCH);
        }
    }

    private boolean isLengthLowerThanGivenField(String field, int length) {
        return field.length() < length;
    }

    private boolean isLengthHigherThanGivenField(String field, int length) {
        return field.length() > length;
    }

    private boolean isLoginExist(String login) {
        return repository.findByLogin(login) != null;
    }
}
