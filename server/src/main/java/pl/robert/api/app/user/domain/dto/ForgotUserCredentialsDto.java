package pl.robert.api.app.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

import static pl.robert.api.app.shared.Constants.M_EMAIL_EMPTY;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotUserCredentialsDto {

    @NotEmpty(message = M_EMAIL_EMPTY)
    String email;
}
