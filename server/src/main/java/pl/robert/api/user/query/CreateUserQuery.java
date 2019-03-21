package pl.robert.api.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter @Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserQuery {

    String login;
    String email;
    String password;
}
