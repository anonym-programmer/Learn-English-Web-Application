package pl.robert.api.app.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthQuery {

    String username;
    String roles;
    boolean isAuthenticated;
}
