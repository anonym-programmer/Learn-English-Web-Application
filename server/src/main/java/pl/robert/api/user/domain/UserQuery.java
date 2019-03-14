package pl.robert.api.user.domain;

import pl.robert.api.user.domain.dto.CreateUserDTO;
import pl.robert.api.user.query.CreateUserQuery;

class UserQuery {

    static CreateUserQuery query(CreateUserDTO dto) {
        return CreateUserQuery
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .build();
    }
}
