package pl.robert.api.app.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static pl.robert.api.app.shared.Constants.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto {

    @NotEmpty(message = M_USERNAME_EMPTY)
    @Size(min = COL_LENGTH_MIN_USERNAME, max = COL_LENGTH_MAX_USERNAME, message = M_USERNAME_LENGTH)
    String username;

    @NotEmpty(message = M_EMAIL_EMPTY)
    @Email(message = M_EMAIL_WRONG_FORMAT)
    String email;

    @NotEmpty(message = M_PASSWORD_EMPTY)
    @Size(min = COL_LENGTH_MIN_PASSWORD, max = COL_LENGTH_MAX_PASSWORD, message = M_PASSWORD_LENGTH)
    String password;

    @NotEmpty(message = M_CONFIRMED_PASSWORD_EMPTY)
    String confirmedPassword;
}
