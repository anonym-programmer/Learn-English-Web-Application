package pl.robert.api.app.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.user.domain.UserConstants;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotUserCredentialsDto implements UserConstants {

    @NotEmpty(message = M_EMAIL_EMPTY)
    String email;
}
