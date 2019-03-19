package pl.robert.api.user.domain;

import pl.robert.api.user.domain.dto.CreateUserDTO;

class UserFactory {

    static User create(CreateUserDTO dto) {
        return User
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
