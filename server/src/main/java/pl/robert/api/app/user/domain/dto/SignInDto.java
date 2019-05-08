package pl.robert.api.app.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.user.domain.Role;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInDto {

    String username;
    String password;
    boolean isEnabled;
    Set<Role> roles = new HashSet<>();
}
