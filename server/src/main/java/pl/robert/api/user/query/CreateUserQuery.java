package pl.robert.api.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
@AllArgsConstructor
public class CreateUserQuery {

    String login;
    String email;
    String password;
}
