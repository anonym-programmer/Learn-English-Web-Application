package pl.robert.api.security.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.user.domain.Role;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorizationDTO {

    String login;
    String password;
    boolean isVerified;
    Set<Role> roles = new HashSet<>();
}
