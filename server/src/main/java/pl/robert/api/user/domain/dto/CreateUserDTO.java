package pl.robert.api.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor
public class CreateUserDTO {

    String login;
    String password;
    String confirmedPassword;
}
