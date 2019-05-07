package pl.robert.api.app.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

import static pl.robert.api.app.shared.Constants.M_EMAIL_EMPTY;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotUserCredentialsDto {

    @NotEmpty(message = M_EMAIL_EMPTY)
    String email;
}
