package pl.robert.api.app.user.domain;

import pl.robert.api.app.user.domain.dto.CreateUserDTO;
import pl.robert.api.app.user.query.CreateUserQuery;

class UserQuery {

    static CreateUserQuery query(CreateUserDTO dto) {
        return CreateUserQuery
                .builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
