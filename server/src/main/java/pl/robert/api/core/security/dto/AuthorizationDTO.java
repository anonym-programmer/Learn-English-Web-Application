package pl.robert.api.core.security.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.user.domain.Role;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorizationDTO {

    String username;
    String password;
    boolean isVerified;
    Set<Role> roles = new HashSet<>();
}
