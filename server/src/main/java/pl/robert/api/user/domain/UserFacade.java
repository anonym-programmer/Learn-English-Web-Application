package pl.robert.api.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.user.domain.dto.CreateUserDTO;
import pl.robert.api.user.query.CreateUserQuery;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserFacade {

    UserRepository repository;

    public CreateUserQuery add(CreateUserDTO dto) {
        repository.saveAndFlush(UserFactory.create(dto));

        return UserQuery.query(dto);
    }
}
