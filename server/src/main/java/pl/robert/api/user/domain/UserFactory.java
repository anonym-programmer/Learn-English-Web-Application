package pl.robert.api.user.domain;

import pl.robert.api.user.domain.dto.CreateUserDTO;

import java.util.Collections;
import java.util.HashSet;

class UserFactory {

    static User create(CreateUserDTO dto) {
        return User
                .builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .roles(new HashSet<>(Collections.singleton(new Role(1L, "ROLE_USER"))))
                .isEnabled(false)
                .build();
    }
}
