package pl.robert.api.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter @Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserQuery {

    String username;
    String email;
    String password;
}
